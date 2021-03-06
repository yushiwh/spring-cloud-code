package cn.springcloud.book.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;

/**
 * 自定义的Zuul Server的过滤器
 *
 * 将header中的gray_mark作为指标
 * 如果带上并且为enable就指向gray-host（针对的是cilent-a中的yml的配置）
 * 否则就指向running-host
 */
public class GrayFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return PRE_DECORATION_FILTER_ORDER - 1;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return !ctx.containsKey(FORWARD_TO_KEY) && !ctx.containsKey(SERVICE_ID_KEY);
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		String mark = request.getHeader("gray_mark");
		if (!StringUtils.isEmpty(mark) && "enable".equals(mark)) {
			RibbonFilterContextHolder.getCurrentContext()
				.add("host-mark", "gray-host");
		} else {
			RibbonFilterContextHolder.getCurrentContext()
				.add("host-mark", "running-host");
		}
		return null;
	}
}
