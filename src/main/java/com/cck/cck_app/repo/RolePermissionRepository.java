//package com.cck.cck_app.repo;
//
//import com.cck.cck_app.entity.RolesPermissions;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface RolePermissionRepository  extends JpaRepository<RolesPermissions, Long> {
//    @Query("""
//        SELECT COUNT(rp) > 0
//        FROM RolesPermissions rp
//        WHERE rp.roles.roleName = :role
//        AND rp.permissions.permission = :permission
//    """)
//    boolean existsByRoleAndPermission(@Param("roles") String role,
//                                      @Param("permission") String permission);
//}
