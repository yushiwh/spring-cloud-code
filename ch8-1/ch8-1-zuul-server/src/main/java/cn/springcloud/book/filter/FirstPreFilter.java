package cn.springcloud.book.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * 第一个自定义的filter，继承ZuulFilter，然后注入到springbean就可以使用
 */
public class FirstPreFilter extends ZuulFilter {

    /**
     * 使用返回值设定Filter类型，可以设置pre,route,post,error
     * <p>
     * public static final String ERROR_TYPE = "error";
     * public static final String POST_TYPE = "post";
     * public static final String PRE_TYPE = "pre";
     * public static final String ROUTE_TYPE = "route";
     *
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 使用返回值设定Filter执行的顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 使用返回值设定该Filter是否执行，可以作为开关来使用
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * Filter里面的核心执行逻辑，业务处理在此编写
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("这是第一个自定义Zuul Filter！");
        return null;
    }
}
