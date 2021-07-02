package com.l024.ljl.service;

import com.l024.ljl.dao.SysTypeDao;
import com.l024.ljl.entity.PageEntity;
import com.l024.ljl.entity.SysTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 试题类型
 */
@Service
public class SysTypeService implements BaseService<SysTypeEntity>{

    @Autowired
    private SysTypeDao sysTypeDao;

    /**
     * 增加试题类型
     * @param sysTypeEntity
     * @return
     */
    @Override
    public boolean add(SysTypeEntity sysTypeEntity) {
        SysTypeEntity save = sysTypeDao.save(sysTypeEntity);
        System.out.println(save);
        if(save!=null)
            return true;
        else
            return false;

    }

    /**
     * 删除试题类型
     * @param id
     * @return
     */
    @Override
    public boolean del(long id) {
        try {
            sysTypeDao.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 获取所有试题类型
     * @return
     */
    @Override
    public List<SysTypeEntity> getAll() {
        return sysTypeDao.findAll();
    }

    /**
     * 更新试题类型
     * @param sysTypeEntity
     * @return
     */
    @Override
    public boolean update(SysTypeEntity sysTypeEntity) {
        SysTypeEntity save = sysTypeDao.save(sysTypeEntity);
        if(save!=null)
            return true;
        return false;
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageEntity<SysTypeEntity> page(int page, int size) {
        return null;
    }
}
