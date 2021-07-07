package com.l024.ljl.service;

import com.l024.ljl.dao.SysUserDao;
import com.l024.ljl.entity.PageEntity;
import com.l024.ljl.entity.SysUserEntity;
import com.l024.ljl.util.StringUtil;
import com.l024.ljl.vo.SpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户相关
 */
@Service
public class SysUserService implements BaseService<SysUserEntity>{
    @Autowired
    private SysUserDao sysUserDao;


    public boolean add(SysUserEntity sysUserEntity) {
        if(sysUserEntity!=null){
            String md5Password = DigestUtils.md5DigestAsHex(sysUserEntity.getPassword().getBytes());
            sysUserEntity.setPassword(md5Password);
            sysUserEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            //设置默认头像
            if(StringUtil.isEmpty(sysUserEntity.getAvator())){
                sysUserEntity.setAvator("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2572099439,3928901169&fm=26&gp=0.jpg");
            }
            SysUserEntity save = sysUserDao.save(sysUserEntity);
            System.out.println(save);
            if(save!=null){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean del(long id) {
        if(id>0){
            sysUserDao.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<SysUserEntity> getAll() {
        Iterable<SysUserEntity> users = sysUserDao.findAll();
        List<SysUserEntity> list = new ArrayList<>();
        users.forEach(user->{list.add(user);});
        return list;
    }

    @Override
    public boolean update(SysUserEntity sysUserEntity) {
        if(sysUserEntity!=null){
            //查询数据库
            SysUserEntity dbUser = sysUserDao.findById(sysUserEntity.getId()).get();
            SpringBeanUtil.copyPropertiesIgnoreNull(sysUserEntity,dbUser);
            SysUserEntity save = sysUserDao.save(dbUser);
            if(save!=null){
                return true;
            }
        }
        return false;
    }

    @Override
    public PageEntity<SysUserEntity> page(int page, int size) {
        if(page<0){
            page = 0;
        }
        if(size<0){
            size = 10;
        }
        PageRequest pageRequest =PageRequest.of(page,size);
        Page<SysUserEntity> fmCompanyEntityPage = sysUserDao.findAll(pageRequest);
        if(fmCompanyEntityPage!=null){
            PageEntity<SysUserEntity> pages = new PageEntity<>();
            pages.setCurrentPage(fmCompanyEntityPage.getNumber());
            pages.setTotalElements(fmCompanyEntityPage.getTotalElements());
            pages.setTotalPage(fmCompanyEntityPage.getTotalPages());
            pages.setContent(fmCompanyEntityPage.getContent());
            return pages;
        }
        return null;
    }

    /**
     * 根据手机号获取用户信息
     * @param phone
     * @return
     */
    public SysUserEntity getUserByPhone(String phone) {
        if(!StringUtil.isEmpty(phone)){
            return sysUserDao.querySysUserEntitiesByPhone(phone);
        }
        return null;
    }

    /**
     * 根据条件查询用户信息
     * @param sysUserEntity
     */
    public SysUserEntity getUserWhere(SysUserEntity sysUserEntity) {
        if(sysUserEntity!=null){
            return sysUserDao.querySysUserEntityByIdOrPhone(sysUserEntity.getId(),sysUserEntity.getPhone());
        }
        return null;
    }

    public long count(){
        return sysUserDao.count();
    }
}
