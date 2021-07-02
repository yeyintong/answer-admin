package com.l024.ljl.service;

import com.l024.ljl.dao.SysSubjectDao;
import com.l024.ljl.dao.SysUserDao;
import com.l024.ljl.dao.SysUserSaveDao;
import com.l024.ljl.entity.SysSubjectEntity;
import com.l024.ljl.entity.SysUserEntity;
import com.l024.ljl.entity.SysUserSaveEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户手长
 */
@Service
public class SysUserSaveService {
    @Autowired
    private SysUserSaveDao sysUserSaveDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysSubjectDao sysSubjectDao;

    public String sava(long userId,long subjectId){
        if (userId>0&&subjectId>0){
            //先查询是否收藏
            SysUserSaveEntity sysUserSaveEntity = sysUserSaveDao.checkSave(userId, subjectId);
            if(sysUserSaveEntity!=null){
                return "已经收藏过了";
            }
            SysUserSaveEntity saveEntity = new SysUserSaveEntity();
            SysUserEntity sysUserEntity = sysUserDao.findById(userId).get();
            SysSubjectEntity sysSubjectEntity = sysSubjectDao.findById(subjectId).get();
            if(sysUserEntity!=null&&sysSubjectEntity!=null){
                saveEntity.setUser(sysUserEntity);
                saveEntity.setSubject(sysSubjectEntity);
                sysUserSaveDao.save(saveEntity);
                return "收藏成功";
            }

        }
        return "收藏失败";
    }

    public List<SysUserSaveEntity> getUserSaves(long userId){
        if(userId>0){
            return sysUserSaveDao.getUserSaves(userId);
        }
        return null;
    }
}
