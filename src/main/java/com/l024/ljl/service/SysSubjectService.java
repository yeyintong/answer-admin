package com.l024.ljl.service;

import com.l024.ljl.dao.SysOptionDao;
import com.l024.ljl.dao.SysSubjectDao;
import com.l024.ljl.dao.SysTypeDao;
import com.l024.ljl.entity.PageEntity;
import com.l024.ljl.entity.SysOptionEntity;
import com.l024.ljl.entity.SysSubjectEntity;
import com.l024.ljl.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 试题
 */
@Service
public class SysSubjectService implements BaseService<SysSubjectEntity> {
    @Autowired
    private SysSubjectDao sysSubjectDao;
    @Autowired
    private SysOptionDao sysOptionDao;
    @Autowired
    private SysTypeDao sysTypeDao;

    /**
     * 增加试题
     * @param sysSubjectEntity
     * @return
     */
    @Transactional
    public boolean add(SysSubjectEntity sysSubjectEntity) {
        boolean flag = true;
        SysSubjectEntity sysSubjectEntity1 = sysSubjectDao.save(sysSubjectEntity);

        if (sysSubjectEntity1 != null) {
            // subject表插入记录成功，继续插入 option表
            // 获取该题目的 id作为选项中的 subject_id
            long subject_id = sysSubjectEntity1.getId();
            // 获取插入成功后的 sysSubjectEntity1 这条记录的选项属性
            List<SysOptionEntity> sysOptionEntityList = sysSubjectEntity1.getOptions();
            // 循环插入每个选项
            if (sysOptionEntityList == null) {
                System.out.println("选项为空");
                return false;
            } else {
                for (SysOptionEntity sysOptionEntity : sysOptionEntityList) {
                    System.out.println(sysOptionEntity);
                    // 设置每一个 sysOptionEntity 中的 subject_id属性
                    sysOptionEntity.setSubject_id(subject_id);
                    // 将每一条 sysOptionEntity 记录插入 option表
                    System.out.println(sysOptionEntity);
                    SysOptionEntity sysOptionEntity1 = sysOptionDao.save(sysOptionEntity);
                    if (sysOptionEntity1 == null) {
                        flag = false;
                    }
                }
            }
            return flag;
        } else {
            return false;
        }
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
        System.out.println(sysSubjectEntity);
        long id = sysSubjectEntity.getId();
        Optional<SysSubjectEntity> subjectEntity = sysSubjectDao.findById(id);

        if (subjectEntity == null) {
            return false;
        } else {
            SysSubjectEntity sysSubjectEntity1 = sysSubjectDao.save(sysSubjectEntity);
            System.out.println(sysSubjectEntity1);
            return true;
        }
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
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
