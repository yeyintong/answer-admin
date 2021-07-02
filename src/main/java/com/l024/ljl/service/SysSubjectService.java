package com.l024.ljl.service;

import com.l024.ljl.dao.SysSubjectDao;
import com.l024.ljl.entity.PageEntity;
import com.l024.ljl.entity.SysSubjectEntity;
import com.l024.ljl.util.StringUtil;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 试题
 */
@Service
public class SysSubjectService implements BaseService<SysSubjectEntity>{
    @Autowired
    private SysSubjectDao sysSubjectDao;

    /**
     * 增加试题
     * @param sysSubjectEntity
     * @return
     */
    @Transactional
    @Override
    public boolean add(SysSubjectEntity sysSubjectEntity) {
        SysSubjectEntity save = sysSubjectDao.save(sysSubjectEntity);
        if(save!=null)
            return true;
        else return false;

    }

    /**
     * 删除试题
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean del(long id) {
        try {
            sysSubjectDao.deleteById(id);

        }catch (Exception e){

            return false;

        }
        return true;
    }

    /**
     * 获取所有试题
     * @return
     */
    @Override
    public List<SysSubjectEntity> getAll() {
        Iterable<SysSubjectEntity> users = sysSubjectDao.findAll();
        List<SysSubjectEntity> list = new ArrayList<>();
        users.forEach(user->{list.add(user);});
        return list;
    }

    /**
     * 更新试题
     * @param sysSubjectEntity
     * @return
     */
    @Transactional
    public boolean update(SysSubjectEntity sysSubjectEntity) {
        sysSubjectDao.save(sysSubjectEntity);
        return true;

    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageEntity<SysSubjectEntity> page(int page, int size) {
        return null;
    }

    /**
     * 根据类型获取相应试题
     * @param typeId
     * @return
     */
    public List<SysSubjectEntity> getAllByTypeId(long typeId){
        if(typeId>0){
            return sysSubjectDao.findSysSubjectEntitiesByTypeId(typeId);
        }
        return null;
    }

    /**
     * 根据题目获取试题
     * @param name
     * @return
     */
    public  List<SysSubjectEntity> findSysSubjectEntitiesByTitle(String name){
        if(!StringUtil.isEmpty(name)){
           return sysSubjectDao.findSysSubjectEntitiesByTitle(name);
        }
        return null;
    }
}
