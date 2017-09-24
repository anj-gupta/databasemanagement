import java.awt.*;  
import java.awt.event.*;
import javax.swing.*;
import java.lang.reflect.*; 
import java.sql.*; 
class Logindb extends JFrame implements ActionListener
{
	JTextField tf1,tf2;
	JLabel l,l1,l2;
	JButton b;
	JPasswordField value;
	database db;
	Logindb()
	{
		l=new JLabel("Login");
		l.setBounds(10,10,50,20);
		l.setFont(new Font("Tahoma",Font.BOLD,16));
		
		l1=new JLabel("Username:");
		l1.setBounds(30,30,100,30);
		l1.setFont(new Font("Tahoma",Font.PLAIN,16));
		
		l2=new JLabel("Password:");
		l2.setBounds(30,70,100,30);
		l2.setFont(new Font("Tahoma",Font.PLAIN,16));
		
		tf1=new JTextField();
		tf1.setBounds(110,35,135,22);
		
		tf2=new JTextField();
		tf2.setBounds(110,75,135,22);
		tf2.setFont(new Font("Webdings",Font.BOLD,16));
		
		//JPasswordField value=new JPasswordField();
		//value.setBounds(110,75,135,22);
		//value.setFont(new Font("Webdings",Font.BOLD,16));
		
		b=new JButton(new ImageIcon("D:\\anuj\\project\\login.jpg"));
		b.setBounds(90,110,70,30);
		b.setFont(new Font("Tahoma",Font.PLAIN,14));
		
		add(l);
		add(l1);
		add(l2);
		add(b);
		add(tf1);
		add(tf2);
		//add(value);
		
		b.addActionListener(this);
		
		setLocation(500,275);
		setSize(300,200);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ev)
	{
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","anuj");
		
		String Username=tf1.getText();
		String Password=tf2.getText();
		//String Password=value.getText();
		
		String query="select * from Login where Username=? and Password=?";
		PreparedStatement st=conn.prepareStatement(query);
		st.setString(1,Username);
		st.setString(2,Password);
		ResultSet rs=st.executeQuery();
		
		if(rs.next())
		{
			 db=new database();
			db.setVisible(true);
			setVisible(false);
			
			//JOptionPane.showMessageDialog(null,"Login successful");
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Invalid Username or Password");
		}
		
		}
		catch(Exception e)
		{
			
			System.out.println(e);
			//JOptionPane.showMessageDialog(null,"please fill data correctly");	
		}
	}
	public static void main(String args[])
	{
		new Logindb();
	}
}
class database extends JFrame implements ActionListener
{  
	JLabel l;
	JButton b1,b2,b3,b4,b5;
	JTextArea ta;
	//Connection conn;
	PreparedStatement pst;
	Add a;
	Delete d;
	Select s;
	Modify m;
	//ResultSet rs;
	database()
	{
		b1=new JButton("Add");
		b2=new JButton("Delete");
		b3=new JButton("Select");
		b4=new JButton("Modify");
		b5=new JButton("ShowAll");
		l=new JLabel("Welcome to the Database");
		ta=new JTextArea("Details of database");
		
		l.setBounds(30,20,200,20);
		b1.setBounds(30,50,75,20);
		b2.setBounds(30,80,75,20);
		b3.setBounds(30,110,75,20);
		b4.setBounds(30,140,75,20);
		b5.setBounds(30,170,80,20);
		ta.setBounds(130,50,400,250);
		
		add(l);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(ta);
		
		setLocation(5,10);
		setSize(570,350);
		setTitle("Database manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
	
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
	
	}
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","anuj");
			
			if(e.getSource()==b1)
			{
				a=new Add();
				a.setVisible(true);
				d.setVisible(false); s.setVisible(false); m.setVisible(false);
			}
			if(e.getSource()==b2)
			{
				d=new Delete();
				d.setVisible(true);
				a.setVisible(false); s.setVisible(false); m.setVisible(false);
			}
			if(e.getSource()==b3)
			{
				s=new Select();
				s.setVisible(true);
				a.setVisible(false); d.setVisible(false); m.setVisible(false);
			}
			if(e.getSource()==b4)
			{
				m=new Modify();
				m.setVisible(true);
				d.setVisible(false); s.setVisible(false); a.setVisible(false);
			}
			if(e.getSource()==b5)
			{
				String SQL="select * from emp";
				pst=conn.prepareStatement(SQL);
				ResultSet rs=pst.executeQuery();
				ta.setText("Eno\t Ename \t Esal \t Email \n");
				
				while(rs.next())
				{
					ta.append(Integer.toString(rs.getInt("eno"))+"\t"+rs.getString("ename")+"\t"+Integer.toString(rs.getInt("esal"))+"\t"+rs.getString("email")+"\n");
				}
				conn.close();
			}
		}
		catch(Exception e1)
		{
			//System.out.println(e);
			//JOptionPane.showMessageDialog(null,e1);
		}
	}
	public static void main(String args[])
	{
		new database();
	}
}
class Add extends JFrame implements ActionListener
{
	JTextField tf1,tf2,tf3,tf4;
	JLabel l1,l2,l3,l4,l5;
	JButton b;
	PreparedStatement pst;
	Connection conn;
	Add()
	{
		l5=new JLabel("Add data in emp table");
		l5.setBounds(20,10,250,20);
		
		l1=new JLabel("Eno:");
		l1.setBounds(20,40,50,20);
		
		l2=new JLabel("Ename:");
		l2.setBounds(20,70,50,20);
		
		l3=new JLabel("Esal:");
		l3.setBounds(20,100,50,20);
		
		l4=new JLabel("Email:");
		l4.setBounds(20,130,50,20);
		
		tf1=new JTextField();
		tf1.setBounds(80,40,100,22);
		
		tf2=new JTextField();
		tf2.setBounds(80,70,100,22);
		
		tf3=new JTextField();
		tf3.setBounds(80,100,100,22);
		
		tf4=new JTextField();
		tf4.setBounds(80,130,100,22);
		
		b=new JButton("Add");
		b.setBounds(50,160,60,25);
		
		
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		
		add(tf1);
		add(tf2);
		add(tf3);
		add(tf4);
		
		add(b);
		
		b.addActionListener(this);
		setLocation(580,270);
		setSize(250,250);
		setTitle("Add");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		//setVisible(true);
	}
	public void actionPerformed(ActionEvent ev)
	{
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","anuj");
		String SQL="insert into emp values(?,?,?,?)";
		pst=conn.prepareStatement(SQL);
		
		pst.setInt(1,Integer.parseInt(tf1.getText()));
		pst.setString(2,tf2.getText());
		pst.setInt(3,Integer.parseInt(tf3.getText()));	
		pst.setString(4,tf4.getText());
		
		int count=pst.executeUpdate();
		tf1.setText(""); tf2.setText(""); tf3.setText(""); tf4.setText("");
		JOptionPane.showMessageDialog(null,"row affected:"+count);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"please fill required field correctly");	
		}
	}
	public static void main(String args[])
	{
		new Add();
	}
}
class Delete extends JFrame implements ActionListener
{
	JLabel l1,l2,l3;
	JTextField tf1;
	JButton b;
	PreparedStatement pst;
	Connection conn;
	Delete()
	{
		l1=new JLabel("Enter the Eno to delete ");
		l2=new JLabel("the data from emp table");
		l3=new JLabel("Eno :");
		tf1=new JTextField();
		b=new JButton("Delete");
		
		l1.setBounds(20,10,150,25);
		l2.setBounds(20,35,160,25);
		l3.setBounds(20,75,50,20);
		tf1.setBounds(60,75,100,20);
		b.setBounds(40,115,70,25);
		
		add(l1);
		add(l2);
		add(l3);
		add(tf1);
		add(b);
		
		b.addActionListener(this);
		
		setLocation(580,270);
		setSize(250,200);
		setLayout(null);
		setTitle("Delete");
		//setVisible(true);
	}
	public void actionPerformed(ActionEvent ev3)
	{
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","anuj");
		String SQL="delete from emp where eno=?";
		pst=conn.prepareStatement(SQL);
		
		pst.setInt(1,Integer.parseInt(tf1.getText()));
		int count=pst.executeUpdate();
		JOptionPane.showMessageDialog(null,"Data deleted and row affected "+count);
		tf1.setText("");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"please fill required field correctly");
		}
	}
	public static void main(String args[])
	{
		new Delete();
	}
}
class Select extends JFrame implements ActionListener
{
	JLabel l1,l2,l3;
	JTextField tf;
	JTextArea ta;
	JButton b;
	PreparedStatement pst;
	Connection conn;
	ResultSet rs;
	Select()
	{
		l1=new JLabel("Enter the Eno you want to select from");
		l2=new JLabel("emp table");
		l3=new JLabel("Eno :");
		b=new JButton("Select");
		ta=new JTextArea();
		tf=new JTextField();
		
		l1.setBounds(10,10,240,25);
		l2.setBounds(10,36,140,25);
		l3.setBounds(20,65,40,20);
		tf.setBounds(70,65,100,20);
		b.setBounds(40,95,70,25);
		ta.setBounds(15,130,300,60);
		
		add(l1);
		add(l2);
		add(l3);
		add(tf);
		add(b);
		add(ta);
		
		setSize(350,250);
		setTitle("Select");
		setLayout(null);
		setLocation(580,270);
		//setVisible(true);
		b.addActionListener(this); 
	}
	public void actionPerformed(ActionEvent ev4)
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","anuj");
			String SQL="select * from emp where eno=?";
			pst=conn.prepareStatement(SQL);
			pst.setInt(1,Integer.parseInt(tf.getText()));
			ResultSet rs=pst.executeQuery();
			rs.next();
			ta.setText("Ename \t Esal \t Email \n");
			ta.append(rs.getString("ename")+"\t"+Integer.toString(rs.getInt("esal"))+"\t"+rs.getString("email"));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"please fill data correctly");
		}
	}
	public static void main(String args[])
	{
		new Select();
	}
}
class Modify extends JFrame implements ActionListener
{
	JTextField tf1,tf2,tf3,tf4;
	JLabel l1,l2,l3,l4,l5;
	JButton b;
	PreparedStatement pst;
	Modify()
	{
		l5=new JLabel("Modify data into emp table");
		l5.setBounds(20,10,250,20);
		
		l1=new JLabel("Eno:");
		l1.setBounds(20,40,50,20);
		
		l2=new JLabel("Ename:");
		l2.setBounds(20,70,50,20);
		
		l3=new JLabel("Esal:");
		l3.setBounds(20,100,50,20);
		
		l4=new JLabel("Email:");
		l4.setBounds(20,130,50,20);
		
		tf1=new JTextField();
		tf1.setBounds(80,40,100,22);
		
		tf2=new JTextField();
		tf2.setBounds(80,70,100,22);
		
		tf3=new JTextField();
		tf3.setBounds(80,100,100,22);
		
		tf4=new JTextField();
		tf4.setBounds(80,130,100,22);
		
		b=new JButton("Modify");
		b.setBounds(50,160,80,25);
		
		
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		
		add(tf1);
		add(tf2);
		add(tf3);
		add(tf4);
		
		add(b);
		
		b.addActionListener(this);
		setLocation(580,270);
		setSize(240,250);
		setTitle("Modify");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		//setVisible(true);
	}
	public void actionPerformed(ActionEvent ev5)
	{
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","anuj");
		String SQL="update emp set ename=?,esal=?,email=? where eno=?";
		pst=conn.prepareStatement(SQL);
		
		pst.setString(1,tf2.getText());
		pst.setInt(2,Integer.parseInt(tf3.getText()));	
		pst.setString(3,tf4.getText());
		pst.setInt(4,Integer.parseInt(tf1.getText()));
		
		
		int count=pst.executeUpdate();
		tf1.setText(""); tf2.setText(""); tf3.setText(""); tf4.setText("");
		JOptionPane.showMessageDialog(null,"row affected:"+count);
		}
		catch(Exception e)
		{
			
			//System.out.println(e);
			JOptionPane.showMessageDialog(null,"please fill required field correctly");	
		}
	}	
	public static void main(String args[])
	{
			new Modify();
	}

}
