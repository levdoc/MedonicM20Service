package com.levdoc.m20service.constants;

import java.util.List;

public interface SecurityConstants {
    List<String> RESOURCES_WHITE_LIST = List.of(
            "/resources/**",
            "/static/**",
            "/",
            "/login",
            "/js/**",
            "/webjars/bootstrap/5.3.0/**",
            "/webjars/bootstrap/5.3.0/css/**",
            "/webjars/bootstrap/5.3.0/js/**",
            "/patient/{uuid}",
            "/patient/{uuid}/analysis/{id}"
    );

    List<String> ADMIN_PERMISSION_LIST = List.of(
            "/admin",
            "/admin/getinfo/user/{id}",
            "/admin/edit/user/{id}",
            "/admin/delete/user/{id}",
            "/admin/block/user/{id}",
            "/admin/unblock/user/{id}",
            "/admin/create/user"
    );

    List<String> DOCTOR_PERMISSION_LIST = List.of(
            "/doctor",
            "/doctor/create/patient",
            "/doctor/view/patient/info/{id}"
    );

    List<String> LABWORKER_PERMISSION_LIST = List.of(
            "/labworker",
            "/labworker/addSimple"
    );

}
