package com.l024.ljl.service;

import com.l024.ljl.dao.SysCommentDao;
import com.l024.ljl.dao.SysSubjectDao;
import com.l024.ljl.dao.SysUserDao;
import com.l024.ljl.dao.SysUserSaveDao;
import com.l024.ljl.entity.SysCommentEntity;
import com.l024.ljl.entity.SysSubjectEntity;
import com.l024.ljl.entity.SysUserEntity;
import com.l024.ljl.entity.SysUserSaveEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户评论
 */
@Service
public class SysCommentService {
    @Autowired
    private SysCommentDao sysCommentDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysSubjectDao sysSubjectDao;

    /**
     * 添加评论
     * @param userId
     * @param subjectId
     * @return
     */
    public boolean sava(long userId,long subjectId,String comment){
        if (userId>0&&subjectId>0){
            SysCommentEntity commentEntity = new SysCommentEntity();
            SysUserEntity sysUserEntity = sysUserDao.findById(userId).get();
            SysSubjectEntity sysSubjectEntity = sysSubjectDao.findById(subjectId).get();
            if(sysUserEntity!=null&&sysSubjectEntity!=null){
                commentEntity.setUser(sysUserEntity);
                commentEntity.setCommentTime(System.currentTimeMillis());
                commentEntity.setSubject(sysSubjectEntity);
                commentEntity.setComment(comment);
                sysCommentDao.save(commentEntity);
                return true;
            }

        }
        return false;
    }

    /**
     * 查询用户评论
     * @param userId
     * @return
     */
    public List<SysCommentEntity> getUserComments(long userId){
        if(userId>0){
            return sysCommentDao.getUserComments(userId);
        }
        return null;
    }

    /**
     * 查询试卷的评论
     * @param subjectId
     * @return
     */
    public List<SysCommentEntity> getSubjectComments(long subjectId){
        if(subjectId>0){
            return sysCommentDao.getSubjectComments(subjectId);
        }
        return null;
    }
}
