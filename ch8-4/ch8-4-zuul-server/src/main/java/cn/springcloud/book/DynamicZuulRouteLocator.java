package cn.springcloud.book;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.util.StringUtils;

import cn.springcloud.book.dao.PropertiesDao;

/**
 * 编写自定义的路由配置加载器
 */
public class DynamicZuulRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    @Autowired
    private ZuulProperties properties;

    @Autowired
    private PropertiesDao propertiesDao;

    public DynamicZuulRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
    }

    @Override
    /**
     * 该方法继承SimpleRouteLocator类
     */
    public void refresh() {
        doRefresh();
    }

    @Override
    /**
     * 该方法继承SimpleRouteLocator并且进行重写规则
     *
     * 主要功能：将配置文件中映射规则信息包装成LinkedHashMap，键是路径的path，值是配置类的封装类
     *
     * 从数据库中加载配置信息，并且结合Zuul内部事件刷新机制。
     * 实际上每次心跳续约都会触发路由器重新加载的操作
     *
     * 自动刷新
     */
    protected Map<String, ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulRoute> routesMap = new LinkedHashMap<>();
        routesMap.putAll(super.locateRoutes());
        routesMap.putAll(propertiesDao.getProperties());
        LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
        routesMap.forEach((key, value) -> {
            String path = key;
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            //键是路径的path，值是配置类的封装类
            values.put(path, value);
        });
        return values;
    }
}
