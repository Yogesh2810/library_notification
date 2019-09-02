import java.lang.*;
import java.util.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.sql.Date;
import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*; 

class facts   //-----------------------------------------------------------------Admin privilage
{
String adminlogin="00";
String adminpass="ss";
}
//-------------------------------------------------------------------------------project class
class project implements ActionListener
{
int zero=0,number;



Statement stmt,stmt1;
ResultSet rs,rs1;
String libid,libpass,libname,libcontact,libemail;
String usrno,usrname,usrroll,usrmobile,usremail;
int usrstatus;
String bookid,bookname,bookauthor,bookloca;
int booktotal,bookavail;
String tag1,tag2,tag3,tag4;
String issueno;
long millis=System.currentTimeMillis();
java.sql.Date today=new java.sql.Date(millis);
String fetched=new String();
Date nova;
int finedueday,fineamount;
Date updatedon;


facts one=new facts();
JFrame f1;
JPanel p1,p2,p3;//p2=adminprivilage  p3=librarian privilage
JTextField tf1;
JPasswordField pf1;
JButton login;

JButton addlib,removelib,home;

JFrame f2;
JPanel librarian,fired;
JLabel l1,l2,l3,l4,l5,l6,logid,logpass;
JTextField tf11,tf12,tf13,tf14,tf15,tf16;
JButton addl,removel;

JButton addusr,removeusr,addbook,removebook,Searchbk,Searchusr,ISSUE,RETURN,updatefine,logout;
JButton cnfrm1,cnfrm2,cnfrm3,cnfrm4,cnfrm5,cnfrm6,cnfrm7,cnfrm8,cnfrm9;

JFrame libpriv;
JPanel usradd,usrrmv,bkadd,bkrmv,bksrch,usrsrch,issu,retu,updtfin;
JLabel userID1,userID2,userID3,Usrname,Usrroll,Usrmob,Usrmail;
JTextField Uid1,Uid2,Uid3,Uname,Uroll,Umob,Uemail;
JLabel bkid,bkid2,bkid3,bkname,bkauthr,bktotal,bkloca,bkt1,bkt2,bkt3,bkt4;
JTextField Bid,Bid2,Bid3,Bname,Bauthor,Btotal,Bloca,T1,T2,T3,T4;

JTextArea bookinfo,usrinfo;

JLabel issno1,issno2,grno,bkno,expret,duedays,amount,updateon;
JTextField isno1,isno2,grnum,bknum,expecreturn,dueday,fineamnt,updatedate;

project()  throws ClassNotFoundException, SQLException
{
//------------------------------------------------------------------------------
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","password");
stmt=con.createStatement();
stmt1=con.createStatement();
//------------------------------------------------------------------------------
logid=new JLabel("User Id");
logpass=new JLabel("Password");
logid.setBounds(300,10,100,50);
logpass.setBounds(300,40,100,50);

f1=new JFrame("project");

p1=new JPanel();
p1.setBackground(Color.gray);
p1.setBounds(0,0,800,400);
p2=new JPanel();
p2.setBackground(Color.gray);
p2.setBounds(0,0,800,400);
p3=new JPanel();
p3.setBackground(Color.gray);
p3.setBounds(0,0,800,400);
tf1=new JTextField(10);
tf1.setBounds(400,25,200,20);
pf1=new JPasswordField(10);
pf1.setBounds(400,55,200,20);
login=new JButton("submit");
login.addActionListener(this);
login.setBackground(Color.white);
login.setBounds(400,100,100,30);
addlib=new JButton("add librarian");
addlib.addActionListener(this);
addlib.setBounds(320,70,200,30);
removelib=new JButton("remove librarian");
removelib.addActionListener(this);
removelib.setBounds(320,170,200,30);
home=new JButton("home");
home.addActionListener(this);
home.setBounds(340,270,150,30);

p2.add(addlib);
p2.add(removelib);
p2.add(home);
f1.add(p1);
p1.add(logid);
p1.add(tf1);
p1.add(logpass);
p1.add(pf1);
p1.add(login);
f1.setSize(800,400);

p1.setLayout(null);
p2.setLayout(null);
f1.setLayout(null);
f1.setVisible(true);
p1.setVisible(true);


//---------------------------------------------------------------
addl=new JButton("Add");
addl.addActionListener(this);
f2=new JFrame("librarian");
f2.setSize(200,600);
f2.setVisible(false);

librarian=new JPanel();
l1=new JLabel("user_id");
tf11=new JTextField(10);
l2=new JLabel("password");
tf12=new JTextField(10);
l3=new JLabel("name");
tf13=new JTextField(10);
l4=new JLabel("contact no");
tf14=new JTextField(10);
l5=new JLabel("email id");
tf15=new JTextField(10);
librarian.add(l1);
librarian.add(tf11);
librarian.add(l2);
librarian.add(tf12);
librarian.add(l3);
librarian.add(tf13);
librarian.add(l4);
librarian.add(tf14);
librarian.add(l5);
librarian.add(tf15);
librarian.add(addl);
f2.add(librarian);

removel=new JButton("remove");
removel.addActionListener(this);
fired=new JPanel();
l6=new JLabel("User_id");
tf16=new JTextField(10);
fired.add(l6);
fired.add(tf16);
fired.add(removel);
f2.add(fired);

//-----------------------------------------------------------------------
addusr=new JButton("add user");
addusr.addActionListener(this);
addusr.setBackground(Color.white);
addusr.setBounds(10,10,150,30);
removeusr=new JButton("remove user");
removeusr.addActionListener(this);
removeusr.setBackground(Color.white);
removeusr.setBounds(10,60,150,30);
Searchusr=new JButton("Search user");
Searchusr.addActionListener(this);
Searchusr.setBackground(Color.white);
Searchusr.setBounds(10,110,150,30);
addbook=new JButton("add book");
addbook.addActionListener(this);
addbook.setBackground(Color.white);
addbook.setBounds(210,10,150,30);
removebook=new JButton("remove book");
removebook.addActionListener(this);
removebook.setBackground(Color.white);
removebook.setBounds(210,60,150,30);
Searchbk=new JButton("Search book");
Searchbk.addActionListener(this);
Searchbk.setBackground(Color.white);
Searchbk.setBounds(210,110,150,30);
ISSUE=new JButton("ISSUE");
ISSUE.addActionListener(this);
ISSUE.setBackground(Color.white);
ISSUE.setBounds(410,10,150,30);
RETURN=new JButton("RETURN");
RETURN.addActionListener(this);
RETURN.setBackground(Color.white);
RETURN.setBounds(410,60,150,30);
updatefine=new JButton("update");
updatefine.addActionListener(this);
updatefine.setBackground(Color.white);
updatefine.setBounds(410,110,150,30);
logout=new JButton("Log out");
logout.addActionListener(this);
logout.setBackground(Color.white);
logout.setBounds(200,300,150,30);
p3.add(addusr);
p3.add(removeusr);
p3.add(Searchusr);
p3.add(addbook);
p3.add(removebook);
p3.add(Searchbk);
p3.add(ISSUE);
p3.add(RETURN);
p3.add(updatefine);
p3.add(logout);
p3.setLayout(null);
p3.setBounds(0,0,800,720);
//------------------------------------------------
libpriv=new JFrame("Library Operations");


//--
usradd=new JPanel();
userID1=new JLabel("User ID");
Usrname=new JLabel("User name");
Usrroll=new JLabel("rollno");
Usrmob=new JLabel("mobile");
Usrmail=new JLabel("email id");
Uid1=new JTextField(15);
Uname=new JTextField(15);
Uroll=new JTextField(15);
Umob=new JTextField(15);
Uemail=new JTextField(15);
cnfrm1=new JButton("Confirm ");
cnfrm1.addActionListener(this);
usradd.add(userID1);
usradd.add(Uid1);
usradd.add(Usrname);
usradd.add(Uname);
usradd.add(Usrroll);
usradd.add(Uroll);
usradd.add(Usrmob);
usradd.add(Umob);
usradd.add(Usrmail);
usradd.add(Uemail);
usradd.add(cnfrm1);

//----
usrrmv=new JPanel();
userID2=new JLabel("User ID");
Uid2=new JTextField(15);
cnfrm2=new JButton("confirm deletion");
cnfrm2.addActionListener(this);
usrrmv.add(userID2);
usrrmv.add(Uid2);
usrrmv.add(cnfrm2);

//----
bkadd=new JPanel();
bkid=new JLabel("bookid");
bkname=new JLabel("bookname");
bkauthr=new JLabel("book author");
bktotal=new JLabel("total books");
bkloca=new JLabel("book location");
bkt1=new JLabel("tag1");
bkt2=new JLabel("tag2");
bkt3=new JLabel("tag3");
bkt4=new JLabel("tag4");
Bid=new JTextField(15);
Bname=new JTextField(15);
Bauthor=new JTextField(15);
Btotal=new JTextField(15);
Bloca=new JTextField(15);
T1=new JTextField(15);
T2=new JTextField(15);
T3=new JTextField(15);
T4=new JTextField(15);
cnfrm3=new JButton("confirm");
cnfrm3.addActionListener(this);
bkadd.add(bkid);
bkadd.add(Bid);
bkadd.add(bkname);
bkadd.add(Bname);
bkadd.add(bkauthr);
bkadd.add(Bauthor);
bkadd.add(bktotal);
bkadd.add(Btotal);
bkadd.add(bkloca);
bkadd.add(Bloca);
bkadd.add(bkt1);
bkadd.add(T1);
bkadd.add(bkt2);
bkadd.add(T2);
bkadd.add(bkt3);
bkadd.add(T3);
bkadd.add(bkt4);
bkadd.add(T4);
bkadd.add(cnfrm3);
//--------

bkrmv=new JPanel();
bkid2=new JLabel("Book id");
Bid2=new JTextField(15);
cnfrm4=new JButton("confirm");
cnfrm4.addActionListener(this);
bkrmv.add(bkid2);
bkrmv.add(Bid2);
bkrmv.add(cnfrm4);
//--------

bksrch=new JPanel();
bkid3=new JLabel("Book id");
Bid3=new JTextField(15);
cnfrm5=new JButton("confirm");
cnfrm5.addActionListener(this);
bookinfo=new JTextArea(20,10);
bksrch.add(bkid3);
bksrch.add(Bid3);
bksrch.add(cnfrm5);
bksrch.add(bookinfo);
//---------

usrsrch=new JPanel();
userID3=new JLabel("user id");
Uid3=new JTextField(15);
cnfrm6=new JButton("confirm");
cnfrm6.addActionListener(this);
usrinfo=new JTextArea(20,10);
usrsrch.add(userID3);
usrsrch.add(Uid3);
usrsrch.add(cnfrm6);
usrsrch.add(usrinfo);
//---------

issu=new JPanel();
issno1=new JLabel("issue no");
grno=new JLabel("user number");
bkno=new JLabel("book id");
expret=new JLabel("Expected return days");
isno1=new JTextField(15);
grnum=new JTextField(15);
bknum=new JTextField(15);
expecreturn=new JTextField(15);
cnfrm7=new JButton("Confirm");
cnfrm7.addActionListener(this);

issu.add(issno1);
issu.add(isno1);
issu.add(grno);
issu.add(grnum);
issu.add(bkno);
issu.add(bknum);
issu.add(expret);
issu.add(expecreturn);
issu.add(cnfrm7);

//---------

retu=new JPanel();
issno2=new JLabel("issue no");
isno2=new JTextField(10);
cnfrm8=new JButton("confirm");
cnfrm8.addActionListener(this);
retu.add(issno2);
retu.add(isno2);
retu.add(cnfrm8);

//---------
updtfin=new JPanel();

} 


public void actionPerformed(ActionEvent e)
{
if (e.getSource()==login)
{

String str1,str2,str3,str4;
str1=tf1.getText();
str2=pf1.getText();
if((str1.equals(one.adminlogin))&&(str2.equals(one.adminpass)))
{
f1.add(p2);
p1.setVisible(false);
p2.setVisible(true);
p3.setVisible(false);
//----------------------------------------------------------------------------------------------------------------------------
try{
int z=stmt.executeUpdate("create table if not exists Librarian(user_id char(10) primary key,password char(15),Name char(20),contact_no char(11),email_id char(30));");
z=stmt.executeUpdate("create table if not exists user(grnumber char(10) primary key,name char(20),classrollno char(6),mobile char(13),email char(30),status int)");
z=stmt.executeUpdate("create table if not exists book(bookid char(10) primary key,name char(30),author char(15),total int,available int,location char(10));");
z=stmt.executeUpdate("create table if not exists issue(issueno char(10) primary key,grnumber char(10),foreign key (grnumber) references user(grnumber),bookid char(10),foreign key(bookid) references book(bookid),issuedate date,expectedreturn date,status char)");
z=stmt.executeUpdate("create table if not exists fine(issueno char(10),foreign key (issueno) references issue(issueno),duedays int,amount int,updatedon date)");
z=stmt.executeUpdate("create table if not exists tagtable(bookid char(10),foreign key (bookid) references book(bookid),tag1 char(20),tag2 char(20),tag3 char(20),tag4 char(20))");
}
catch(Exception t)
{
//System.out.println(t);
	JFrame fin;
	fin=new JFrame();
	JOptionPane.showMessageDialog(fin,t);
}
//------------------------------------------------------------------------------------------------------------------------------
}

else
{
try{
rs=stmt.executeQuery("select * from Librarian");
while(rs.next())
{
	
str3=rs.getString("user_id");
str4=rs.getString("password");
if((str1.equals(str3))&&(str2.equals(str4)))
{
f1.add(p3);
p1.setVisible(false);
p2.setVisible(false);
p3.setVisible(true);
}

}

}
catch(Exception ff)
{System.out.println(ff);}

}
}

if(e.getSource()==addlib)
{
f2.setVisible(true);
f2.remove(fired);
f2.add(librarian);
librarian.setVisible(true);


}

if(e.getSource()==addl)
{
libid=tf11.getText();

libpass=tf12.getText();

libname=tf13.getText();

libcontact=tf14.getText();

libemail=tf15.getText();

try{
int z=stmt.executeUpdate("insert into Librarian values('"+libid+"','"+libpass+"','"+libname+"','"+libcontact+"','"+libemail+"')");
tf11.setText("");
tf12.setText("");
tf13.setText("");
tf14.setText("");
tf15.setText("");

JFrame fin;
fin=new JFrame();
JOptionPane.showMessageDialog(fin,"Librarian Added");
}
catch(Exception nana)
{
//System.out.println(nana);
	JFrame fin;
	fin=new JFrame();
	JOptionPane.showMessageDialog(fin,nana);
}

}


if(e.getSource()==removelib)
{
f2.remove(librarian);
f2.add(fired);
fired.setVisible(true);
f2.setVisible(true);

}

if(e.getSource()==removel)
{
libid=tf16.getText();

try{
int z=stmt.executeUpdate("delete from Librarian where user_id='"+libid+"'");
JFrame fin;
fin=new JFrame();
JOptionPane.showMessageDialog(fin,"Done");
}
catch(Exception nana)
{
//System.out.println(nana);
	JFrame fin;
	fin=new JFrame();
	JOptionPane.showMessageDialog(fin,nana);
}

}

if(e.getSource()==home)
{
p2.setVisible(false);
p1.setVisible(true);
tf1.setText("");
pf1.setText("");

}

if(e.getSource()==addusr)
{
	liboperation();
	Umob.setText("+91");
	libpriv.setVisible(true);
	libpriv.add(usradd);
	
	
}

if(e.getSource()==cnfrm1)
{
	usrno=Uid1.getText();
	usrname=Uname.getText();
	usrroll=Uroll.getText();
	usrmobile=Umob.getText();
	usremail=Uemail.getText();
	
	addusr();
}

if(e.getSource()==removeusr)
{
	liboperation();
	libpriv.setVisible(true);
	libpriv.add(usrrmv);
}
if(e.getSource()==cnfrm2)
{
	
	usrno=Uid2.getText();
	System.out.println(usrno);
	rmvusr();

}

if(e.getSource()==addbook)
{
	liboperation();
	libpriv.setSize(180,500);
	libpriv.setVisible(true);
	libpriv.add(bkadd);
	
}

if(e.getSource()==cnfrm3)
{
bookid=Bid.getText();
bookname=Bname.getText();
bookauthor=Bauthor.getText();
booktotal=Integer.parseInt(Btotal.getText());
bookavail=booktotal;
bookloca=Bloca.getText();
tag1=T1.getText();
tag2=T2.getText();
tag3=T3.getText();
tag4=T4.getText();
addbook();
}

if(e.getSource()==removebook)
{
	liboperation();
	libpriv.setSize(180,500);
	libpriv.setVisible(true);
	libpriv.add(bkrmv);

}

if(e.getSource()==cnfrm4)
{
	bookid=Bid2.getText();
	rmvbook();

}

if(e.getSource()==Searchbk)
{
	liboperation();
	libpriv.setSize(180,500);
	libpriv.setVisible(true);
	libpriv.add(bksrch);
	bookinfo.setText(" ");
	
}

if(e.getSource()==cnfrm5)
{
	bookid=Bid3.getText();
	srchbook();
}

if(e.getSource()==Searchusr)
{
	liboperation();
	libpriv.setSize(200,500);
	libpriv.setVisible(true);
	libpriv.add(usrsrch);
	usrinfo.setText(" ");

}

if(e.getSource()==cnfrm6)
{
	usrno=Uid3.getText();
	srchusr();
}


if(e.getSource()==ISSUE)
{
	liboperation();
	libpriv.setSize(180,500);
	libpriv.setVisible(true);
	libpriv.add(issu);

}

if(e.getSource()==cnfrm7)
{
issueno=isno1.getText();
usrno=grnum.getText();
bookid=bknum.getText();
number=Integer.parseInt(expecreturn.getText());
bkissue();

}

if(e.getSource()==RETURN)
{
	liboperation();
	libpriv.setSize(180,500);
	libpriv.setVisible(true);
	libpriv.add(retu);
}

if(e.getSource()==cnfrm8)
{
issueno=isno2.getText();
bkreturn();

}


if(e.getSource()==logout)
{
	liboperation();
	f1.remove(p3);
	f1.remove(p2);
	f1.add(p1);
	p1.setVisible(true);
	f1.setVisible(true);
	tf1.setText("");
	pf1.setText("");
}

if(e.getSource()==updatefine)
{
	updtrecords();
    
}




}
//------------------------------------------------------------------------------------------
void addusr()
{
try{
int z=stmt.executeUpdate("insert into user values('"+usrno+"','"+usrname+"','"+usrroll+"','"+usrmobile+"','"+usremail+"',"+zero+")");
Uid1.setText("");
Uname.setText("");
Uroll.setText("");
Umob.setText("+91");
Uemail.setText("");
JFrame fin;
fin=new JFrame();
JOptionPane.showMessageDialog(fin,"Member added successfully");
}
catch(Exception nana)
{
//System.out.println(nana);
	JFrame fin;
	fin=new JFrame();
	JOptionPane.showMessageDialog(fin,nana);
}
}
//------------------------------------------------------------------------------------------
void rmvusr()
{
try{
	System.out.println(usrno);
	int z=stmt.executeUpdate("delete from user where grnumber='"+usrno+"'");
	JFrame fin;
	fin=new JFrame();
	JOptionPane.showMessageDialog(fin,"done");
}
catch(Exception nana)
{
//System.out.println(nana);
	JFrame fin;
	fin=new JFrame();
	JOptionPane.showMessageDialog(fin,nana);
}
}
//------------------------------------------------------------------------------------------
void srchusr()
{
try{
	rs=stmt.executeQuery("select * from user where grnumber='"+usrno+"'");
	while(rs.next())
	{
	usrno=rs.getString("grnumber");
	usrname=rs.getString("name");
	usrroll=rs.getString("classrollno");
	usrmobile=rs.getString("mobile");
	usremail=rs.getString("email");
	usrstatus=rs.getInt("status");
	}
	usrinfo.setText("\n user id="+usrno+"\nname="+usrname+"\n roll no="+usrroll+"\n mobile no="+usrmobile+"\n Email id ="+usremail+"\nStatus="+usrstatus);
	
}
catch(Exception nana)
{
//System.out.println(nana);
JFrame fin;
fin=new JFrame();
JOptionPane.showMessageDialog(fin,nana);
}
}
//------------------------------------------------------------------------------------------
void addbook()
{
try{
int z=stmt.executeUpdate("insert into book values('"+bookid+"','"+bookname+"','"+bookauthor+"',"+booktotal+","+bookavail+",'"+bookloca+"')");
z=stmt.executeUpdate("insert into tagtable values('"+bookid+"','"+tag1+"','"+tag2+"','"+tag3+"','"+tag4+"')");
JFrame fin;
fin=new JFrame();
JOptionPane.showMessageDialog(fin,"Book Added");
}
catch(Exception nana)
{
//System.out.println(nana);
	JFrame fin;
	fin=new JFrame();
	JOptionPane.showMessageDialog(fin,nana);
}
}
//------------------------------------------------------------------------------------------
void rmvbook()
{
try{
int z=stmt.executeUpdate("delete from tagtable where bookid='"+bookid+"'");
z=stmt.executeUpdate("delete from book where bookid='"+bookid+"'");

JFrame fin;
fin=new JFrame();
JOptionPane.showMessageDialog(fin,"Done");
}
catch(Exception nana)
{
//System.out.println(nana);
	JFrame fin;
	fin=new JFrame();
	JOptionPane.showMessageDialog(fin,nana);
}
}
//------------------------------------------------------------------------------------------
void srchbook()
{
try{
	rs=stmt.executeQuery("select * from book where bookid='"+bookid+"'");
	while(rs.next())
	{
	bookid=rs.getString("bookid");
	bookname=rs.getString("name");
	bookauthor=rs.getString("author");
	booktotal=rs.getInt("total");
	bookavail=rs.getInt("available");
	bookloca=rs.getString("location");
	}
	bookinfo.setText("\n book id="+bookid+"\nname="+bookname+"\nauthor ="+bookauthor+"\n total="+booktotal+"\n Available ="+bookavail+"\nAddress="+bookloca);
	
}
catch(Exception nana)
{
//System.out.println(nana);
JFrame fin;
fin=new JFrame();
JOptionPane.showMessageDialog(fin,nana);
}
}
//------------------------------------------------------------------------------------------
void addtags()
{
try{
int z=stmt.executeUpdate("insert into tagtable values('"+bookid+"','"+tag1+"','"+tag2+"','"+tag3+"','"+tag4+"')");
}
catch(Exception nana)
{
//System.out.println(nana);
	JFrame fin;
	fin=new JFrame();
	JOptionPane.showMessageDialog(fin,nana);
}
}
//------------------------------------------------------------------------------------------
void bkissue()
{
	try{
		int z=stmt.executeUpdate("insert into issue values('"+issueno+"','"+usrno+"','"+bookid+"','"+today+"',"+number+",'y')");
		z=stmt.executeUpdate("update user set status =status+1 where grnumber='"+usrno+"'");
		z=stmt.executeUpdate("update book set available =available-1 where bookid='"+bookid+"'");
		}
		catch(Exception nana)
		{
		//System.out.println(nana);
			JFrame fin;
			fin=new JFrame();
			JOptionPane.showMessageDialog(fin,nana);
		}

}
//------------------------------------------------------------------------------------------
void bkreturn()
{
	try{
		int z=stmt.executeUpdate("update issue set status ='n' where issueno='"+issueno+"'");
		z=stmt.executeUpdate("delete from fine where issueno='"+issueno+"'");
		z=stmt.executeUpdate("update user set status =status-1 where grnumber='"+usrno+"'");
		z=stmt.executeUpdate("update book set available =available+1 where bookid='"+bookid+"'");
		}
		catch(Exception nana)
		{
		//System.out.println(nana);
		JFrame fin;
		fin=new JFrame();
		JOptionPane.showMessageDialog(fin,nana);
		}

}
//------------------------------------------------------------------------------------------
void updtrecords()
{
	try{
		rs=stmt.executeQuery("truncate table fine");
		rs=stmt.executeQuery("select * from issue where status='y'");
		while(rs.next())
		{
		issueno=rs.getString("issueno");
		usrno=rs.getString("grnumber");
		bookid=rs.getString("bookid");
		fetched=rs.getString("issuedate");
		nova=Date.valueOf(fetched);
		number=rs.getInt("expectedreturn");
		long diff=today.getTime()-nova.getTime();
		long diffDays=diff/(24*60*60*1000);
		if(diffDays>number)
		{
			finedueday=(int) (diffDays-number);
		}
		else
		{
			finedueday=0;	
		}
		if((finedueday>0)&&(finedueday<15))
		{
			fineamount=finedueday*2;
		}
		if((finedueday>=15)&&(finedueday<30))
		{
			fineamount=(finedueday*5)-75+30;
		}
		if(finedueday>30)
		{
			fineamount=(finedueday*10)-300+30+75;
		}
		int z=stmt.executeUpdate("insert into fine values('"+issueno+"',"+finedueday+","+fineamount+",'"+today+"')");
		}
        		
	}
	catch(Exception nana)
	{
	System.out.println(nana);
		JFrame fin;
		fin=new JFrame();
		JOptionPane.showMessageDialog(fin,nana);
	}
	notification();
	
}
String email_id_of_student="one",temp="one";

void notification()
{
	try{
	
	rs=stmt.executeQuery("select * from fine");
	while(rs.next())
	{
    String id=rs.getString("issueno");
    rs1=stmt1.executeQuery("select grnumber from issue where issueno='"+id+"'");
    while(rs1.next())
    	temp=rs1.getString("grnumber");
    rs1=stmt1.executeQuery("select email from user where grnumber='"+temp+"'");
    while(rs1.next())
    	email_id_of_student=rs1.getString("email");
    System.out.println(email_id_of_student);
    finedueday=rs.getInt("duedays");
	fineamount=rs.getInt("amount");
    String s1="hello Student \n this is gentle reminder to you that \n You are late for returning your issued books to library \n details are as follows \n";
	String s2=" due days = ";
	String s3="\nfine amount =";
	String s4="\nYou are informed to return the book immediately and pay the fine above mentioned";
	String s5=s1.concat(s2)+finedueday+s3+fineamount+s4;
	send("libmansys9@gmail.com","libman123",email_id_of_student,"library notification",s5);
	}
	}
	catch(Exception c)
	{
	System.out.println(c);
		JFrame fin;
		fin=new JFrame();
		JOptionPane.showMessageDialog(fin,c);
	}
	

}

void liboperation()
{
	libpriv.setSize(260,500);
	libpriv.setVisible(false);
	libpriv.remove(usradd);
	libpriv.remove(usrrmv);
	libpriv.remove(bkadd);
	libpriv.remove(bkrmv);
	libpriv.remove(bksrch);
	libpriv.remove(usrsrch);
	libpriv.remove(issu);
	libpriv.remove(retu);
	libpriv.remove(updtfin);

}
//------------------------------------------------------------------------------------------


void send(String from,String password,String to,String sub,String msg){  
    //Get properties object 
    Properties props = new Properties();    
    props.put("mail.smtp.host", "smtp.gmail.com");    
    props.put("mail.smtp.socketFactory.port", "465");    
    props.put("mail.smtp.socketFactory.class",    
              "javax.net.ssl.SSLSocketFactory");    
    props.put("mail.smtp.auth", "true");    
    props.put("mail.smtp.port","465");    
    //get Session   
    Session session = Session.getDefaultInstance(props,    
     new javax.mail.Authenticator() {    
     protected PasswordAuthentication getPasswordAuthentication() {    
     return new PasswordAuthentication("+EMAILID+","+EMAILPASSWORD+");  
     }    
    });    
    //compose message    
    try {    
     MimeMessage message = new MimeMessage(session);    
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
     message.setSubject(sub);    
     message.setText(msg);    
     //send message  
     Transport.send(message);    
    // System.out.println("message sent successfully");    
 	JFrame fin;
 	fin=new JFrame();
 	JOptionPane.showMessageDialog(fin,"message sent successfully");
    } catch (MessagingException e) {throw new RuntimeException(e);}        
}  



public static void main(String[] args)  throws ClassNotFoundException, SQLException
{

project obj=new project();

}
}

