import java.awt.Button;
import java.awt.Color;
//import java.awt.WindowadAdapter;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;
class firstpage extends Frame implements ActionListener
{
    Button bt1,bt2;
    JLabel us,pa;
    JTextField userid,pass1;
    boolean issameuid=false;
    boolean issamepass=false;
    static int pq;
    firstpage(){
    super("login page");
    //setBackground();
   // addWindowListener(new WindowadAdapter(){public void windowClosing(WindowEvent e) { System.exit(0);}});
    setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
    bt1 = new Button("LOGIN");
    bt2= new Button("sign up!!");
    us = new JLabel("username:");
    pa = new JLabel("password:");
    userid= new JTextField(15);
   pass1= new JPasswordField(15);
    setSize(320,200);
    //bt1.setSize(100,25);
    bt1.setPreferredSize(new DimensionUIResource(100, 25));
    bt2.setPreferredSize(new DimensionUIResource(100, 25));
    //bt1.setLocation(300,180);
    add(us);
    add(userid);
    add(pa);
    add(pass1);
    add(bt1);
    //add(bt2);
    bt1.addActionListener(this);
    setVisible(true);
    try{
    File f = new File("C:\\Users\\"+System.getProperty("user.name") +"\\Desktop\\java project");
        if(!f.exists())
        {
            f.createNewFile();
        }
    } catch(IOException e){}
    }
    public void actionPerformed(ActionEvent a)
    {
        if(Checkuid()&&checkpass())
        {
            bt1.setLabel("success!!!");
            secondpage sp = new secondpage();
        }
    }
    boolean Checkuid()
    {
        String uid="";
        try
        {

        File f = new File("C:\\Users\\"+System.getProperty("user.name") +"\\Desktop\\java project\\username.txt");
        if(!f.exists())
        {
            f.createNewFile();
        }
        File f1 = new File("C:\\Users\\"+System.getProperty("user.name") +"\\Desktop\\java project\\password.txt");
        if(!f1.exists())
        {
            f1.createNewFile();
        }
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\"+System.getProperty("user.name") +"\\Desktop\\java project\\username.txt"));
        String str=null;
        List<String> lines = new ArrayList<String>();
        while((str = in.readLine()) != null){
            lines.add(str);
        }
        String[] userArray = lines.toArray(new String[lines.size()]); 
        if(userArray.length==0)
        {
            JOptionPane.showMessageDialog(this, "no usernames or passwords available sorry!!!\n please enter a few", "oopps!!!",JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        
            
        }
        uid=userid.getText();
        for(int i =0;i<userArray.length;i++)
        {
            if(uid.equals(userArray[i]))
            {
                 issameuid=true; 
                 pq=i;
            }
        }
        in.close();
    }
    catch(IOException e)
    {

    }
    
    return issameuid;
    }
    boolean checkpass()
    {
        String pass="";
        try
        {
        BufferedReader out = new BufferedReader(new FileReader("C:\\Users\\"+System.getProperty("user.name") +"\\Desktop\\java project\\password.txt"));
        String str=null;
        List<String> lines = new ArrayList<String>();
        while((str = out.readLine()) != null){
            lines.add(str);
        }
        String[] passArray = lines.toArray(new String[lines.size()]); 
        pass=pass1.getText();
            if(pass.equals(passArray[pq]))
            {
                 issamepass=true; 
            }
            out.close();
    }
    catch(IOException e)
    {

    }
    return issamepass;
    }
    public static void main(String[] z)
   {
        new firstpage();
        //new secondpage();
        
   }
}
        
class secondpage extends Frame implements ActionListener
{
    Button bt2;
    JTextArea t1;
    JLabel l1;
    secondpage()
    {
       super("text locker");
       setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
       setBackground(Color.lightGray);
       bt2= new Button("save and close");
       bt2.addActionListener(this);
       t1= new JTextArea(10,20);
      //t1.setColumns(20);       
       //t1.setBounds(10, 20, 40, 40);
       l1= new JLabel("enter your text here:");
       try
       {
       String str;
       //File f1 = new File("C:\\Users\\R.N.V Siva Karthik\\Desktop\\java project\\info.txt");
       BufferedReader out = new BufferedReader(new FileReader("C:\\Users\\"+System.getProperty("user.name") +"\\Desktop\\java project\\info.txt"));
       //List<String> lines = new ArrayList<String>();
       while((str = out.readLine()) != null){
        t1.append(str);
    }
    out.close();
       
}
catch(Exception e){}
       add(l1);
       add(t1);
       add(bt2);
       setSize(320,300);
       setVisible(true); 
    }
    public void actionPerformed(ActionEvent e)
    {
        try
        {
        BufferedWriter br= new BufferedWriter(new FileWriter("C:\\Users\\"+System.getProperty("user.name") +"\\Desktop\\java project\\info.txt",false));
        //FileWriter br = new FileWriter(new File(, getName("C:\\Users\\R.N.V Siva Karthik\\Desktop\\java project\\info.txt")));
        br.write(t1.getText());
         JOptionPane.showMessageDialog(this, "saved to file", "saved!!",JOptionPane.WARNING_MESSAGE);
        br.close();
        System.exit(0);
        }
        catch(IOException ae)
        {

        }
    }
}
