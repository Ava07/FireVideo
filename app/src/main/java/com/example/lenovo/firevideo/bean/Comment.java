package com.example.lenovo.firevideo.bean;

import cn.bmob.v3.BmobObject;

//评论类
public class Comment extends BmobObject {
    private String CommentId;//评论的ID
    private String UserId;//发起评论的用户ID
    private  Integer  Comment_Total_Like;//总点赞量
    private String Comment_Content;//评论内容
    public Comment(){

    }

    public void setCommentId(String commentId) {
        CommentId = commentId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setComment_Content(String comment_Content) {
        Comment_Content = comment_Content;
    }

    public  void setComment_Total_Like(Integer comment_Total_Like) {
        Comment_Total_Like = comment_Total_Like;
    }

    public String getCommentId() {
        return CommentId;
    }

    public String getUserId() {
        return UserId;
    }

    public String getComment_Content() {
        return Comment_Content;
    }

    public  Integer getComment_Total_Like() {
        return Comment_Total_Like;
    }
}
