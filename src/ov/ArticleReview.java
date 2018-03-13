package ov;

public class ArticleReview{

	//评论id
	public String id="id";
	//文章id
	public String a_id="a_id";
	//评论用户id
	public String c_id="c_id";
	//评论内容
	public String content="content";
	//评论日期
	public String date="date";
	//评论点赞数
	public int like_size=0;
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getA_id(){
		return a_id;
	}
	public void setA_id(String a_id){
		this.a_id=a_id;
	}
	public String getC_id(){
		return c_id;
	}
	public void setC_id(String c_id){
		this.c_id=c_id;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date=date;
	}
	public int getLike_size(){
		return like_size;
	}
	public void setLike_size(int like_size){
		this.like_size=like_size;
	}

	@Override
	public String toString(){
		return "review:\n"+
				"id="+this.getId()+"\n"+
				"a_id="+this.getA_id()+"\n"+
				"c_id="+this.getC_id()+"\n"+
				"content="+this.getContent()+"\n"+
				"likesize="+this.getLike_size();
	}
}
