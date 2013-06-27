package cn.ecust.bs.guuguu.service;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-26
 */
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

import cn.ecust.bs.guuguu.domain.Role;
import cn.ecust.bs.guuguu.domain.User;


public class GuuGuuUserDetails implements UserDetails {

private static final long serialVersionUID = 1L;
private final User user;

    private final String role_Hierarchy="ROLE_ADMIN>ROLE_USER ROLE_USER>ROLE_BROWSER";
    public GuuGuuUserDetails(User user) {
        this.user = user;
    }


    @Override
    public Collection<GrantedAuthority> getAuthorities() {
         Role[] roles = user.getRoles();
         if (roles ==null) return Collections.emptyList();
   
         RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
         roleHierarchy.setHierarchy(role_Hierarchy);
         List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
         for(Role role: roles)
         {
        	 authorities.add(new SimpleGrantedAuthority(role.name()));
         }
         Collection<? extends GrantedAuthority> roless= roleHierarchy.getReachableGrantedAuthorities(authorities);
         List<GrantedAuthority> allroles = new ArrayList<GrantedAuthority>();
         for(GrantedAuthority a: roless)
         {
            allroles.add(a);
         }
         return allroles;
    }
  
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
}