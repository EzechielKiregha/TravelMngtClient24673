package com.mirrorcompany.community.dao;

import com.mirrorcompany.community.model.Group;
import com.mirrorcompany.dao.HibernateUtil;
import com.mirrorcompany.model.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GroupDao {

    public boolean createGroup(Group group) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(group);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean joinGroup(Long userId, Long groupId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query userQuery = session.createQuery("FROM User u WHERE u.userId = :userId");
            userQuery.setParameter("userId", userId);
            List<User> userList = userQuery.list();
            if (!userList.isEmpty()) {
                User user = userList.get(0);
                Query groupQuery = session.createQuery("FROM Group g WHERE g.groupId = :groupId");
                groupQuery.setParameter("groupId", groupId);
                List<Group> groupList = groupQuery.list();
                if (!groupList.isEmpty()) {
                    Group group = groupList.get(0);
                    group.getMembers().add(user);
                    session.update(group);
                    transaction.commit();
                    return true;
                }
            }
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean leaveGroup(Long userId, Long groupId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query userQuery = session.createQuery("FROM User u WHERE u.userId = :userId");
            userQuery.setParameter("userId", userId);
            List<User> userList = userQuery.list();
            if (!userList.isEmpty()) {
                User user = userList.get(0);
                Query groupQuery = session.createQuery("FROM Group g WHERE g.groupId = :groupId");
                groupQuery.setParameter("groupId", groupId);
                List<Group> groupList = groupQuery.list();
                if (!groupList.isEmpty()) {
                    Group group = groupList.get(0);
                    group.getMembers().remove(user);
                    session.update(group);
                    transaction.commit();
                    return true;
                }
            }
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Group> getGroupsByUserId(Long userId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT g FROM Group g JOIN g.members m WHERE m.userId = :userId");
            query.setParameter("userId", userId);
            List<Group> g = query.list();
            return g;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}