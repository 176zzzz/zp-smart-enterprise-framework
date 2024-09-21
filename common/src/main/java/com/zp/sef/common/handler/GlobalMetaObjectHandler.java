package com.zp.sef.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zp.sef.common.utils.SysUserUtil;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * GlobalMetaObjectHandler
 *
 * @author ZP
 */
@Slf4j
@Component
public class GlobalMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATE_TIME_COL = "createTime";

    private static final String UPDATE_TIME_COL = "updateTime";

    private static final String CREATE_USER_ID = "createUserId";

    private static final String UPDATE_USER_ID = "updateUserId";

    private static final String CREATE_USER_IP = "createUserIp";

    private static final String UPDATE_USER_IP = "updateUserIp";


    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, CREATE_TIME_COL, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, CREATE_USER_ID, String.class, SysUserUtil.getLoginUserId());
        this.strictInsertFill(metaObject, CREATE_USER_IP, String.class, SysUserUtil.getLoginUserIp());
        updateAll(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        updateAll(metaObject);
    }

    private void updateAll(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, UPDATE_TIME_COL, LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, UPDATE_USER_ID, String.class, SysUserUtil.getLoginUserId());
        this.strictUpdateFill(metaObject, UPDATE_USER_IP, String.class, SysUserUtil.getLoginUserIp());
    }

}
