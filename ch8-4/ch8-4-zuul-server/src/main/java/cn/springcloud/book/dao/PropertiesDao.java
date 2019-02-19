package cn.springcloud.book.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.springcloud.book.entity.ZuulRouteEntity;

@Component
/**
 * 从数据中读取路由的配置信息
 * 映射到实体表中并且封装成一个map供加载器进行使用
 * 注意这里的list.forEach的写法，跟 foreach (var item in list)在性能上面差别不是很大
 * 具体差别
 * 1、在foreach中是不能使用list.Remove(),否则在进入下一个循环就会报异常，所以，如果有使用之后就必须break;
 * （所以，想删除list中的项，最好不用使用foreach和list.ForEach,而是使用for或list.RemoveAll）
 *
 * 2、在list.ForEach()中不能使用continue或者break *
 * 如果在遍历到某个特殊项的时候，不用遍历后面的项，需要break，这种情况使用foreach
 */
public class PropertiesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String SQL = "SELECT * FROM zuul_route WHERE enabled = TRUE";

    public Map<String, ZuulRoute> getProperties() {
        Map<String, ZuulRoute> routes = new LinkedHashMap<>();
        List<ZuulRouteEntity> list = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(ZuulRouteEntity.class));
        list.forEach(entity -> {
            if (StringUtils.isEmpty(entity.getPath())) return;
            ZuulRoute zuulRoute = new ZuulRoute();
            //创建一个跟ZuulRoute一模一样的实体类进行接收后，直接进行bean的copy
            BeanUtils.copyProperties(entity, zuulRoute);
            routes.put(zuulRoute.getPath(), zuulRoute);
        });
        return routes;
    }
}
