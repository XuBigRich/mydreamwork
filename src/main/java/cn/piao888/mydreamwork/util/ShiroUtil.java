package cn.piao888.mydreamwork.util;

import cn.piao888.mydreamwork.domain.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {
    /**
     * 获取当前 Subject
     *
     * @return Subject
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }
    /**
     * 获取封装的 ShiroUser
     *
     * @return ShiroUser
     */
    public static ShiroUser getUser() {
            return (ShiroUser) getSubject().getPrincipals().getPrimaryPrincipal();
    }
}
