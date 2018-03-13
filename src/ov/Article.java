package ov;

public class Article{

	//文章id
	public String id="id";
	//公众号id
	public String a_id="a_id";
	//文章标题
	public String title="title";
	//文章日期
	public String date="date";
	//文章内容
	public String content="content";
	//评论用户id
	public String u_id="u_id";
	//文章点赞数
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
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date=date;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getU_id(){
		return u_id;
	}
	public void setU_id(String u_id){
		this.u_id=u_id;
	}
	public int getLike_size(){
		return like_size;
	}
	public void setLike_size(int like_size){
		this.like_size=like_size;
	}

	@Override
	public String toString(){
		return "article:\n"+"\n"+
				"id="+this.getId()+"\n"+
				"a_id="+this.getA_id()+"\n"+
				"title="+this.getTitle()+"\n"+
				"content="+this.getContent()+"\n"+
				"u_id="+this.getU_id()+"\n"+
				"like_size="+this.getLike_size();
	}
}
