package com.hcl.cms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SubjectCrud {
	public String insertSubjects(Subjects subject) {
		SessionFactory sf=HibernateUtil.getConnection();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		s.save(subject);
		t.commit();
		return "Record Inserted...";
		
	}
	public String insertFeedBack(FeedBack feedback) {
		SessionFactory sf=HibernateUtil.getConnection();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		s.save(feedback);
		t.commit();
		return "FeedBack Inserted...";
	}
	public List<String> showInstructor() {
		SessionFactory sf=HibernateUtil.getConnection();
		Session s=sf.openSession();
		Query q=s.createQuery("select distinct instructor from Subjects");
		List lstSubjects=q.list();
		List<String>inslist=new ArrayList<String>();
		for (Object object : lstSubjects) {
			inslist.add((String)object);
		}
		return inslist;
	}
	
public String feedBackId() {
	SessionFactory sf = HibernateUtil.getConnection();
	Session s = sf.openSession();
	Query q = s.createQuery("from FeedBack");
	List<String> lst = q.list();
	int count=0;
	String fid = "";
	count=lst.size();
	count++;
	
     
		String formatted = String.format("%03d", count);
	fid = "C"+formatted;
	
	return fid;
	}
public List<String> showSubject(String instructor){
	SessionFactory sf=HibernateUtil.getConnection();
	Session s=sf.openSession();
	
	Query q=s.createQuery("select distinct subject from Subjects where instructor='" +instructor+ " '");
	List<String> lstSubjects=q.list();
	System.out.println(lstSubjects);
	return lstSubjects;
}

public List<FbResult> feedbackList(String instructor,String subject){

SessionFactory sf=HibernateUtil.getConnection();
Session s=sf.openSession();
Query q=s.createQuery(" select fbvalue,count(*) from FeedBack "
+ " where instructor='" +instructor+ "' and subject='" +subject +"' "
+ " group by fbvalue");

List lst=q.list();
List<FbResult> list=new ArrayList<FbResult>();
FbResult res=null;
for (Object ob : lst) {
Object[] row=(Object[])ob;
res=new FbResult();
res.setFbvalue(row[0].toString());
res.setCount(Integer.parseInt(row[1].toString()));
System.out.println(row[0] + "  " +row[1]);
list.add(res);
}
return list;
}
}
