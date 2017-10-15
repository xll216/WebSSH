package com.lanou.action;

import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蓝鸥科技有限公司  www.lanou3g.com.
 */
public class HrAction extends ActionSupport {
    private List<Department> departments;
    private List<Post> postList;
    private String selectDepartId;

    {
        departments = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Department depart = new Department();
            depart.setId(i);
            depart.setDname("部门--" + i);

            List<Post> posts = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Post post = new Post();
                post.setId(j);
                post.setPname(i + "--职务--" + j);
                posts.add(post);
            }
            depart.setPostList(posts);

            departments.add(depart);
        }
    }

    /**
     * 获取所有部门
     **/
    public String findAllDepartment() {
        for (Department depart : departments) {
            System.out.println(depart);
        }
        postList = departments.get(0).getPostList();
        return SUCCESS;
    }

    /**
     * 根据部门id获取职务集合
     **/
    public String findPostListByDepartId() throws IOException {

        int index = Integer.parseInt(selectDepartId);
        postList = departments.get(index).getPostList();

        JsonConfig jsonConfig = new JsonConfig();

        jsonConfig.setExcludes(new String[]{"text"});//排除不需要的属性
        String jsonArray = JSONArray.fromObject(postList, jsonConfig).toString();

        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(jsonArray);

        return null;
    }


    public String findPost() {
        int index = Integer.parseInt(selectDepartId);
        postList = departments.get(index).getPostList();

        return SUCCESS;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public String getSelectDepartId() {
        return selectDepartId;
    }

    public void setSelectDepartId(String selectDepartId) {
        this.selectDepartId = selectDepartId;
    }
}
