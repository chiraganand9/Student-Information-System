import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
public class Student extends JFrame 
{		
 		//Declaring Variables
		boolean inAnApplet = true;   
		final boolean shouldFill = true;
    	final boolean shouldWeightX = true;

    	//Welcome panel TAB
		final static String panelstr0 = " Welcome ";
    				
		//Login panel TAB
		final static String panelstr1 = " LOGIN ";
		
		//Student Detail panel TAB		
		final static String panelstr2 = " ADD STUDENT DETAILS ";
		
		//Modify or Delete Student Details panel TAB		
		final static String panelstr3 = " MODIFY / DELETE STUDENT DETAILS ";
				
		//Applying courier new fonts for the controls used		
		Font dFont = new Font("courier new",Font.PLAIN,12);
		Font tFont = new Font("courier new",Font.BOLD,14);
		
		//Applying listners for handling radio button events
		RadioListener alistener = new RadioListener();	
		
		//Applying listners for handling check box events
		CheckBoxListener ckblistener = new CheckBoxListener();
		
		//Applying listners for handling combo box events
		ComboBoxListener cmblistener = new ComboBoxListener();
		
		//Applying gridbag layout
		GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc1 = new GridBagConstraints();	
		GridBagConstraints gbc2 = new GridBagConstraints();	
		GridBagConstraints gbc3 = new GridBagConstraints();	
		
		//Dialog Box to ask delete confirmation messages 
		Container cpane = getContentPane();		
		JDialog dlg = new JDialog((Frame) null, " Delete Confirmation!!!", true);
		Container dlgCPane = dlg.getContentPane();		
		
		//Grouping the Radio Buttons 
		ButtonGroup bg1 = new ButtonGroup();
		ButtonGroup bg2 = new ButtonGroup();
		ButtonGroup bg3 = new ButtonGroup();
		
		// Declaring the Labels
		JLabel jwelcome, jlogin, jpass, jstudentname, jpreaddress, jpermaaddress,  jfathername, jgender, jcourse, jyear,  jlabel, jdob, jfatheroccupation, jphone,jlabel111;
		JLabel jmstudentname, jmpreaddress, jmpermaaddress,  jmfathername, jmgender, jmcourse, jmyear,  jmlabel, jmdob, jmfatheroccupation, jmphone; 
		JLabel jLabeldlg = new JLabel("Are you sure?");
				
		// Declaring the Radio buttons 
		JRadioButton male, female, m_male, m_female;		
			
		// Declaring the Check boxes 
		JCheckBox dmc, degree, charcerti, ncc, mdmc, mdegree, mcharcerti, mncc;	
		JCheckBox ckstudentID, ckstudentname, ckpreaddress, ckpermaaddress, ckfathername, ckgender, ckdob;
		
		// Declaring the combo boxes 
		JComboBox cb_course, cb_year ,mcb_course, mcb_year, cb_sname, cb_fatheroccupation, mcb_fatheroccupation;	
		JComboBox jstudID, jeq;
		
		// Declaring the text boxes 
		JTextField tlogname, tsname, tspreaddress, tspermaaddress, tsphone, tsdob, tsfathername, tsfatheroccupation;
		JPasswordField tlogpass = new JPasswordField(40);	
		JTextField tmname, tmpreaddress, tmpermaaddress, tmphone, tmdob, tmfathername, tmfatheroccupation;
					
		// Declaring the String type variables
		String driver="sun.jdbc.odbc.JdbcOdbcDriver";
		String url="jdbc:odbc:student";		
		String title_dialog = "Student Record Added";
		String fail_dialog = "Login Error";
		String add_dialog = "Database Record Added";
		String modify_dialog = "Database Record Updated";
		String del_dialog = "Database Record Deleted";
		String gender = "male";
		String year = "I Year";
		String course = "BSc(Comp. Sc.)";
		String foccupation = "Govt. Employee";
		String currentname = "";
		String tempname = "";
		String qry = "";
		String condsel="No Condition", studID="StudentID", eq="=";	
		String query1, query2, query3, query4, query5, query6, query7, message_dialog, nsel, selname, reg_no,str;
		
		 //Declaring the Integer type variables
		int dmcsel, degreesel, charcfsel, ncccfsel, mdmcsel, mdegreesel, mcharcfsel, mnccsel;
		int type_dialog = JOptionPane.PLAIN_MESSAGE;
		int ctr_val=0,selstudentID=0,selstudentname=0,selstudentpreadd=0, selstudentpermaadd, selfathername=0,selgender=0, seldob=0;
		
		//Declaring the JPanel & Tabbed panes 
		JPanel pan0, pan1, pan2, pan3;
		JTabbedPane tbPane = new JTabbedPane();
		
		// Declaring the Submit Buttons 		
		JButton delit = new JButton("YES");
		JButton delno = new JButton("NO");
		JButton login, cont, save, mod_button, del;
			
		//Building JTable for student reports 
		Object[] data = new Object[5];
	   	DefaultTableModel defaulttablemodel = new DefaultTableModel();
   		JTable jtable = new JTable(defaulttablemodel);	
		
   		//Starting with the main() method   													
		public static void main(String args[])
 		{
  			Student stud = new Student();
 		}
		//------------------------- Ending with the main() method -----------------
		
		//========================= Starting with the Student constructor =========
 		public Student() 
 		{
			// Declaring title for the application 
  			super("STUDENT INFORMATION MANAGEMENT SYSTEM"); 
  			setUp();
  			pack();
  			addWindowListener(new WindowEventHandler());
  			setSize(640,480);
  			show();
 		}
		//--------------------------Ending with the Student Constructor--------------
		
		//Starting the setUp() method to create the File menu on panel
		void setUp() 
 		{
  			setUpMenuBar(); //Calling the setUpMenuBar() to create the Exit Menu item
			displayPanel0();	//Calling a method that creates a panel and displays the Exit menu item
			cpane.add(tbPane, BorderLayout.CENTER);
  		}
		//------------Ending with the setUp() method---------
		
		//-----------Starting with the setUpMenuBar() method to create File menu
 		void setUpMenuBar() 
		{
  			  MenuBar mBar = new MenuBar();
			  Menu mfile = new Menu("File");		
			  		// Creating the Exit menu item
			  		MenuItem xfile = new MenuItem("Exit");		
						// Handling this menu item on click event to Exit 	  			  
			  			xfile.addActionListener(new MenuItemHandler()); 
			  			mfile.add(xfile);					
			  		mBar.add(mfile);
			  setMenuBar(mBar);
 		}	
//--------------------------Ending with the setUpMenuBar() method--------------				

//-----------Starting with the displayPanel0() method to create the Welcome panel

		void displayPanel0()
		{
			pan0 = new JPanel(); //Creating a Welcome Panel
			pan0.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
			pan0.setFont(tFont);
			pan0.setLayout(grid);  						
			jwelcome = new JLabel("Student Information Management  System ");	
			Font f = new Font("Helvetica", Font.BOLD + Font.ITALIC, 30);
			jwelcome.setFont(f);
			gbc1.ipady=1;
			gbc1.ipadx=1;
			gbc1.gridx=0;
			gbc1.gridy=1;
			grid.setConstraints(jwelcome,gbc1);
			pan0.add(jwelcome);
			
			jlabel111=new JLabel("      					");
			gbc1.ipady=1;
			gbc1.ipadx=1;
			gbc1.gridx=0;
			gbc1.gridy=2;
			grid.setConstraints(jlabel111,gbc1);
			pan0.add(jlabel111);
			cont = new JButton("Click to Continue");
						gbc1.ipady = 1;       						
						gbc1.ipadx = 1;  
						gbc1.gridx = 0;       						     
			        	gbc1.gridy = 3;    				
		        		grid.setConstraints(cont, gbc1);
			pan0.add(cont);										
			cont.addActionListener(new ButtonHandler());  
			tbPane.addTab(panelstr0, pan0);
	 }				
//--------------------------Ending with the displayPanel0() method--------------				

//-----------Starting with the displayPanel1() method to create the Login panel

	 	void displayPanel1()
		{
			pan1 = new JPanel(); //LOGIN TAB
			pan1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
			pan1.setFont(tFont);
			pan1.setLayout(grid);  						
				jlogin = new JLabel("Login Name ");
				jlogin.setFont(dFont);// Make this Data Font that we declared before
				    //gbc1.weightx = 0.0;    //Horizontal Space not required here		             						
			        gbc1.weighty = 0.0;   	  //Vertical Space	
					gbc1.ipady = 2;       	  //Height of Component				
					gbc1.ipadx = 2; 		  //Width of Component
			        gbc1.anchor = GridBagConstraints.WEST; 	
			      	gbc1.gridwidth = 1;   	  //1 columns wide				
			        gbc1.gridx = 0;       	  //aligned with login 0  -- Very Important 					   
			        gbc1.gridy = 0;       	  //0th Row  	
					// Adding all these features to this Label, jlogin
			        grid.setConstraints(jlogin, gbc1);
					// Adding the Label "jlogin" onto the LOGIN TAB
		        pan1.add(jlogin);				
				tlogname = new JTextField(20);
			    	gbc1.ipady = 2;       						
					gbc1.ipadx = 2;       						
		        	gbc1.weighty = 0.0;   						
		        	gbc1.anchor = GridBagConstraints.WEST; 	
		       		gbc1.gridwidth = 5;   						
		        	gbc1.gridx = 1;       						    
		        	gbc1.gridy = 0;       						
		        	grid.setConstraints(tlogname, gbc1);
		        pan1.add(tlogname);				
				jpass = new JLabel("Password");
				jpass.setFont(dFont);
			        gbc1.ipady = 2;       						
					gbc1.ipadx = 2;       						
			        gbc1.weighty = 0.0;   						
			        gbc1.anchor = GridBagConstraints.WEST; 	
			        gbc1.gridwidth = 5;   						
			        gbc1.gridx = 0;       						     
			        gbc1.gridy = 1;       						
			        grid.setConstraints(jpass, gbc1);
			     pan1.add(jpass);				
				tlogpass = new JPasswordField(20);
			    	gbc1.ipady = 2;       						
					gbc1.ipadx = 2;       						
		        	gbc1.weighty = 0.0;   						
		        	gbc1.anchor = GridBagConstraints.WEST; 	
		       		gbc1.gridwidth = 2;   						
		        	gbc1.gridx = 1;       						     
		        	gbc1.gridy = 1;       						
		        	grid.setConstraints(tlogpass, gbc1);
		        pan1.add(tlogpass);				
				login = new JButton("LOGIN");
		        	gbc1.ipady = 2;       						
					gbc1.ipadx = 2;       						
		        	gbc1.weighty = 0.0;   						
		        	gbc1.anchor = GridBagConstraints.CENTER; 	        
					gbc1.gridwidth = 1;   						
		        	gbc1.gridx = 1;       						      
		        	gbc1.gridy = 2;       						
		        	grid.setConstraints(login, gbc1);
		        pan1.add(login);										
				login.addActionListener(new ButtonHandler());  				
			tbPane.addTab(panelstr1, pan1);
			}
		//------------------------------------------------------------ Ending with displayPanel1() method -----------
		//============================================================ Starting with displayPanel2() method ===========
		void displayPanel2() //ADD STUDENT  DETAILS TAB 
		{
				   pan2 = new JPanel();
				   pan2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
				   pan2.setLayout(grid);					  		   
				   jstudentname = new JLabel("Student Name");	
				   jstudentname.setFont(dFont);		
						gbc1.fill = GridBagConstraints.BOTH;		
						gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 0;       						     
			        	gbc1.gridy = 1;       						
			        	grid.setConstraints(jstudentname, gbc1);
			       	pan2.add(jstudentname);															
					tsname = new JTextField(20);
						gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 1;       						     
			        	gbc1.gridy = 1;       						
			        	grid.setConstraints(tsname, gbc1);				
					pan2.add(tsname);									
					jdob = new JLabel("Date of Birth");
					jdob.setFont(dFont);				
						gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 0;       						     
			        	gbc1.gridy = 2;       						
			        	grid.setConstraints(jdob, gbc1);				
					pan2.add(jdob);													
					tsdob = new JTextField(20);	
					gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 1;       						     
			        	gbc1.gridy = 2;       						
			        	grid.setConstraints(tsdob, gbc1);				
					pan2.add(tsdob);				
					jfathername = new JLabel("Father Name");	
						jfathername.setFont(dFont);				
						gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 0;       						     
			        	gbc1.gridy = 3;       						
			        	grid.setConstraints(jfathername, gbc1);				
					pan2.add(jfathername);	
					tsfathername = new JTextField(20);
						gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 1;       						     
			        	gbc1.gridy = 3;       						
			        	grid.setConstraints(tsfathername, gbc1);				
					pan2.add(tsfathername);				
					jfatheroccupation = new JLabel("Father Occupation");	
					jfatheroccupation.setFont(dFont);	
						gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 0;       						     
			        	gbc1.gridy = 4;       						
			        	grid.setConstraints(jfatheroccupation, gbc1);
			        	pan2.add(jfatheroccupation);					
			        	cb_fatheroccupation = new JComboBox();
						cb_fatheroccupation.setFont(dFont);	
						// Adding Elements to combo box
				 		cb_fatheroccupation.addItem(" Govt. Employee ");
		    			cb_fatheroccupation.addItem(" Self Employed ");
						cb_fatheroccupation.addItem(" Business ");
						gbc1.fill = GridBagConstraints.BOTH;
						gbc1.insets = new Insets(10,0,0,30);  		
		    			gbc1.ipady = 2;     
						gbc1.ipadx = 2;		
		       	        gbc1.gridx = 1;	
		        		gbc1.gridy = 4;	
						cb_fatheroccupation.addItemListener(new ComboBoxListener());			
		        		grid.setConstraints(cb_fatheroccupation, gbc1);
		        	    pan2.add(cb_fatheroccupation);		
					jpreaddress = new JLabel("Present Address");	
						jpreaddress.setFont(dFont);				
						gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 0;       						     
			        	gbc1.gridy = 5;       						
			        	grid.setConstraints(jpreaddress, gbc1);		
					pan2.add(jpreaddress);
					tspreaddress = new JTextField(20);
						gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 1;       						     
			        	gbc1.gridy = 5;   
						grid.setConstraints(tspreaddress, gbc1);	
					pan2.add(tspreaddress);	
					jpermaaddress = new JLabel("Permanent Address");	
						jpermaaddress.setFont(dFont);				
						gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 0;       						     
			        	gbc1.gridy = 6;       						
			        	grid.setConstraints(jpermaaddress, gbc1);		
					pan2.add(jpermaaddress);
					tspermaaddress = new JTextField(20);
						gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 1;       						     
			        	gbc1.gridy = 6;   
						grid.setConstraints(tspermaaddress, gbc1);	
					pan2.add(tspermaaddress);	
					jphone = new JLabel(" Telephone Number ");	
					jphone.setFont(dFont);		
						gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 0;       						     
			        	gbc1.gridy = 7;       						
			        	grid.setConstraints(jphone, gbc1);		
					pan2.add(jphone);
				tsphone = new JTextField(20);
						gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 1;       						     
			        	gbc1.gridy = 7;   
						grid.setConstraints(tsphone, gbc1);	
					pan2.add(tsphone);						
					// Checking the default gender as male by using 'true' 
					jgender = new JLabel("Gender");	
					jgender.setFont(dFont);	
						gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 0;       						     
			        	gbc1.gridy = 8;       						
			        	grid.setConstraints(jgender, gbc1);
			        	pan2.add(jgender);					
			    	male = new JRadioButton("Male",true);
						male.setFont(dFont);		
						gbc1.fill = GridBagConstraints.BOTH;
						gbc1.ipady = 2;       						
		       			gbc1.ipadx = 2;  
		       			gbc1.gridx = 1;
		        		gbc1.gridy = 8;
		        		grid.setConstraints(male, gbc1);
						male.addActionListener(new RadioListener());
						bg1.add(male);
					pan2.add(male);	
	   				female = new JRadioButton("Female",false);
						female.setFont(dFont);		
						gbc1.fill = GridBagConstraints.BOTH;
						gbc1.ipady = 2;       						
		       			gbc1.ipadx = 2;  
		       			gbc1.gridx = 2;
		        		gbc1.gridy = 8;
		        		grid.setConstraints(female, gbc1);								
						female.addActionListener(new RadioListener());	
						bg1.add(female);
					pan2.add(female);
				jcourse = new JLabel(" Course ");	
						jcourse.setFont(dFont);				
						gbc1.ipady = 2;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 0;       						     
			        	gbc1.gridy = 9;       						
			        	grid.setConstraints(jcourse, gbc1);				
					pan2.add(jcourse);	
					cb_course = new JComboBox();
						// Applying Fonts inside the combo box as courier new 
						cb_course.setFont(dFont);	
						// Adding the elements to combo box
				 		cb_course.addItem("BSc(Comp.Sc.)");
						cb_course.addItem("BCA");
		    			// Filling it align properly with other Elements inside the TAB
		    			gbc1.fill = GridBagConstraints.BOTH;
						gbc1.insets = new Insets(10,0,0,30);  		
						gbc1.ipady = 2;     						
						gbc1.ipadx = 2;							
		       	        gbc1.gridx = 1;							
		        		gbc1.gridy = 9;							
						cb_course.addItemListener(new ComboBoxListener());	
		        		grid.setConstraints(cb_course, gbc1);
		        		pan2.add(cb_course);
					cb_year = new JComboBox();
						cb_year.setFont(dFont);	
						// Adding the combo box elements to combo box
				 		cb_year.addItem(" I Year ");
		    			cb_year.addItem(" II Year ");
						cb_year.addItem(" III Year ");
						gbc1.fill = GridBagConstraints.BOTH;
						gbc1.insets = new Insets(10,0,0,30);  		
		    			gbc1.ipady = 2;     
						gbc1.ipadx = 2;		
		       	        gbc1.gridx = 2;	
		        		gbc1.gridy = 9;			
						cb_year.addItemListener(new ComboBoxListener());			
		        		grid.setConstraints(cb_year, gbc1);
		        	    pan2.add(cb_year);			
					dmc = new JCheckBox("Detail Mark Sheet Given");
						//Using check boxes
						gbc1.fill = GridBagConstraints.BOTH;
						gbc1.ipady = 0;       						
						gbc1.ipadx = 0;  
						gbc1.gridx = 1;       						     
			        	gbc1.gridy = 10; 						
						grid.setConstraints(dmc, gbc1);
						dmc.setFont(dFont);
						dmc.addItemListener(new CheckBoxListener());
    				pan2.add(dmc);										
					degree = new JCheckBox("Degree Awarded");
						gbc1.ipady = 0;       						
						gbc1.ipadx = 0;  
						gbc1.gridx = 2;       						     
			        	gbc1.gridy = 10; 
						grid.setConstraints(degree, gbc1);
						degree.setFont(dFont);
						degree.addItemListener(new CheckBoxListener());
    				pan2.add(degree);							
					charcerti = new JCheckBox("Character Certificate Given ");
						gbc1.ipady = 0;       						
						gbc1.ipadx = 0;  
						gbc1.gridx = 1;       						     
			        	gbc1.gridy = 11; 
						grid.setConstraints(charcerti, gbc1);
						charcerti.setFont(dFont);
						charcerti.addItemListener(new CheckBoxListener());
    				pan2.add(charcerti);		
					ncc = new JCheckBox("NCC Certificate Given");
						gbc1.ipady = 0;       						
						gbc1.ipadx = 0;  
						gbc1.gridx = 2;       						     
			        	gbc1.gridy = 11;
						grid.setConstraints(ncc, gbc1);
						ncc.setFont(dFont);
						ncc.addItemListener(new CheckBoxListener());
    				pan2.add(ncc);	
					save = new JButton("SAVE RECORD");										
						gbc1.ipady = 1;       						
						gbc1.ipadx = 2;  
						gbc1.gridx = 2;       						     
			        	gbc1.gridy = 12;  
						grid.setConstraints(save, gbc1);	
					// Handling the SAVERECORD button on click event by ButtonHandler() class
					save.addActionListener(new ButtonHandler());
					pan2.add(save);
					tbPane.addTab(panelstr2,pan2);

		}
		//-------------------------------------------------------------- Ending with displayPanel2() method ---------
		//============================================================ Starting displayPanel3() method =========
		void displayPanel3() //MODIFY/DELETE STUDENT DETAILS TAB
		{
				   pan3 = new JPanel();
				   pan3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
				   pan3.setLayout(grid);	
				   cb_sname = new JComboBox();
				   cb_sname.addItem("Select Student Name");
					cb_sname.setFont(dFont);	
					gbc2.fill = GridBagConstraints.BOTH;		
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 2;       						     
			        	gbc2.gridy = 1;       						
			        	grid.setConstraints(cb_sname, gbc2);			
						cb_sname.addItemListener(new ComboBoxListener());		
					pan3.add(cb_sname);		
				   jmstudentname = new JLabel("Student Name");	
				   jmstudentname.setFont(dFont);		
						gbc2.fill = GridBagConstraints.BOTH;		
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 0;       						     
			        	gbc2.gridy = 2;       						
			        	grid.setConstraints(jmstudentname, gbc2);
			       	pan3.add(jmstudentname);															
					tmname = new JTextField(20);
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 1;       						     
			        	gbc2.gridy = 2;       						
			        	grid.setConstraints(tmname, gbc2);				
					pan3.add(tmname);									
					jmdob = new JLabel("Date of Birth ");
					jmdob.setFont(dFont);				
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 0;       						     
			        	gbc2.gridy = 3;       						
			        	grid.setConstraints(jmdob, gbc2);				
					pan3.add(jmdob);													
					tmdob = new JTextField(20);	
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 1;       						     
			        	gbc2.gridy = 3;       						
			        	grid.setConstraints(tmdob, gbc2);				
					pan3.add(tmdob);				
					jmfathername = new JLabel("Father's Name");	
						jmfathername.setFont(dFont);				
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 0;       						     
			        	gbc2.gridy = 4;       						
			        	grid.setConstraints(jmfathername, gbc2);				
					pan3.add(jmfathername);	
				tmfathername = new JTextField(20);
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 1;       						     
			        	gbc2.gridy = 4;       						
			        	grid.setConstraints(tmfathername, gbc2);				
					pan3.add(tmfathername);				
					jmfatheroccupation = new JLabel("Father's Occupation");	
					jmfatheroccupation.setFont(dFont);	
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 0;       						     
			        	gbc2.gridy = 5;       						
			        	grid.setConstraints(jmfatheroccupation, gbc2);
			        	pan3.add(jmfatheroccupation);					
			        	mcb_fatheroccupation = new JComboBox();
						mcb_fatheroccupation.setFont(dFont);	
						// Adding the combo box elements to combo box
				 		mcb_fatheroccupation.addItem(" Govt. Employee ");
		    			mcb_fatheroccupation.addItem(" Self Employed ");
						mcb_fatheroccupation.addItem(" Business ");
						gbc2.fill = GridBagConstraints.BOTH;
						gbc2.insets = new Insets(10,0,0,30);  		
		    			gbc2.ipady = 2;     
						gbc2.ipadx = 2;		
		       	        gbc2.gridx = 1;	
		        		gbc2.gridy = 5;			
						mcb_fatheroccupation.addItemListener(new ComboBoxListener());			
		        		grid.setConstraints(mcb_fatheroccupation, gbc2);
		        	    pan3.add(mcb_fatheroccupation);																
					jmpreaddress = new JLabel("Present Address");	
						jmpreaddress.setFont(dFont);				
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 0;       						     
			        	gbc2.gridy = 6;       						
			        	grid.setConstraints(jmpreaddress, gbc2);		
					pan3.add(jmpreaddress);

					tmpreaddress = new JTextField(20);
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 1;       						     
			        	gbc2.gridy = 6;   
						grid.setConstraints(tmpreaddress, gbc2);	
					pan3.add(tmpreaddress);						

					JLabel jmpermaaddress = new JLabel("Permanent Address ");	
						jmpermaaddress.setFont(dFont);				
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 0;       						     
			        	gbc2.gridy = 7;       						
			        	grid.setConstraints(jmpermaaddress, gbc2);		
					pan3.add(jmpermaaddress);	
 					tmpermaaddress = new JTextField(20);
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 1;       						     
			        	gbc2.gridy = 7;   
						grid.setConstraints(tmpermaaddress, gbc2);	
					pan3.add(tmpermaaddress);	
					jmphone = new JLabel(" Telephone Number ");	
						jmphone.setFont(dFont);				
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 0;       						     
			        	gbc2.gridy = 8;       						
			        	grid.setConstraints(jmphone, gbc2);		
					pan3.add(jmphone);
					tmphone = new JTextField(20);
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 1;       						     
			        	gbc2.gridy = 8;   
						grid.setConstraints(tmphone, gbc2);	
					pan3.add(tmphone);						
					// Checking the default gender as male by using true
					jmgender = new JLabel("Gender");	
					jmgender.setFont(dFont);	
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 0;       						     
			        	gbc2.gridy = 9;       						
			        	grid.setConstraints(jmgender, gbc2);
			        	pan3.add(jmgender);					
					
					m_male = new JRadioButton("Male",true);
						m_male.setFont(dFont);		
						gbc2.fill = GridBagConstraints.BOTH;
						gbc2.ipady = 2;       						
		       			gbc2.ipadx = 2;  
		       			gbc2.gridx = 1;
		        		gbc2.gridy = 9;
		        		grid.setConstraints(m_male, gbc2);
						m_male.addActionListener(alistener);
						bg2.add(m_male);
					pan3.add(m_male);	
	   				m_female = new JRadioButton("Female",false);
						m_female.setFont(dFont);		
						gbc2.fill = GridBagConstraints.BOTH;
						gbc2.ipady = 2;       						
		       			gbc2.ipadx = 2;  
		       			gbc2.gridx = 2;
		        		gbc2.gridy = 9;
		        		grid.setConstraints(m_female, gbc2);								
						m_female.addActionListener(alistener);	
						bg2.add(m_female);
					pan3.add(m_female);
					jmcourse = new JLabel(" Course ");	
						jmcourse.setFont(dFont);				
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 0;       						     
			        	gbc2.gridy = 10;       						
			        	grid.setConstraints(jmcourse, gbc2);				
					pan3.add(jmcourse);	
					mcb_course = new JComboBox();
					mcb_course.setFont(dFont);	
						// Adding the combo box elements to combo box
				 		mcb_course.addItem("BSc(Comp.Sc.)");
						mcb_course.addItem("BCA");
		    			// Fillig it to align properly with other Elements inside the TAB
		    			gbc2.fill = GridBagConstraints.BOTH;
						gbc2.insets = new Insets(10,0,0,30);  		
						gbc2.ipady = 2;     						
						gbc2.ipadx = 2;							
		       	        gbc2.gridx = 1;							
		        		gbc2.gridy = 10;							
						mcb_course.addItemListener(new ComboBoxListener());	
		        		grid.setConstraints(mcb_course, gbc2);
		        		pan3.add(mcb_course);
						mcb_year = new JComboBox();
						mcb_year.setFont(dFont);	
						// Adding Elements to combo box
				 		mcb_year.addItem(" I Year ");
		    			mcb_year.addItem(" II Year ");
						mcb_year.addItem(" III Year ");
						gbc2.fill = GridBagConstraints.BOTH;
						gbc2.insets = new Insets(10,0,0,30);  		
		    			gbc2.ipady = 2;     
						gbc2.ipadx = 2;		
		       	        gbc2.gridx = 2;	
		        		gbc2.gridy = 10;			
						mcb_year.addItemListener(new ComboBoxListener());			
		        		grid.setConstraints(mcb_year, gbc2);
		        	    pan3.add(mcb_year);			
					mdmc = new JCheckBox("Detail Mark Sheet Given");
					gbc2.fill = GridBagConstraints.BOTH;
						gbc2.ipady = 0;       						
						gbc2.ipadx = 0;  
						gbc2.gridx = 1;       						     
			        	gbc2.gridy = 11; 						
						grid.setConstraints(mdmc, gbc2);
						mdmc.setFont(dFont);
						mdmc.addItemListener(new CheckBoxListener());
    				pan3.add(mdmc);										
					mdegree = new JCheckBox("Degree Awarded");
						gbc2.ipady = 0;       						
						gbc2.ipadx = 0;  
						gbc2.gridx = 2;       						     
			        	gbc2.gridy = 11; 
						grid.setConstraints(mdegree, gbc2);
						mdegree.setFont(dFont);
						mdegree.addItemListener(new CheckBoxListener());
    				pan3.add(mdegree);							
					mcharcerti = new JCheckBox("Character Certificate Given ");
						gbc2.ipady = 0;       						
						gbc2.ipadx = 0;  
						gbc2.gridx = 1;       						     
			        	gbc2.gridy = 12; 
						grid.setConstraints(mcharcerti, gbc2);
						mcharcerti.setFont(dFont);
						mcharcerti.addItemListener(new CheckBoxListener());
    				pan3.add(mcharcerti);		
					mncc = new JCheckBox("NCC Certificate Given");
						gbc2.ipady = 0;       						
						gbc2.ipadx = 0;  
						gbc2.gridx = 2;       						     
			        	gbc2.gridy = 12;
						grid.setConstraints(mncc, gbc2);
						mncc.setFont(dFont);
						mncc.addItemListener(new CheckBoxListener());
    				pan3.add(mncc);	
					del = new JButton("DELETE");										
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 1;       						     
			        	gbc2.gridy = 13;  
						grid.setConstraints(del, gbc2);
						pan3.add(del);
						del.addActionListener(new ButtonHandler());											
					mod_button = new JButton("MODIFY");										
						gbc2.ipady = 2;       						
						gbc2.ipadx = 2;  
						gbc2.gridx = 2;       						     
			        	gbc2.gridy = 13;  
						grid.setConstraints(mod_button, gbc2);
						pan3.add(mod_button);
						mod_button.addActionListener(new ButtonHandler());					
						tbPane.addTab(panelstr3, pan3);	
			accessStudName();
		}
		//-------------------------------------------------------------- Ending with displayPanel3() method ---------			
		//---------------------------------------------------------------Starting with displayPanes() method---------
		void displayPanes() // ACCOUNTANT LOGGEDIN
		{	
			displayPanel2();
			displayPanel3();
		}		
		
		//========================================================== Starting with  verifyLogin() =============	
		void verifyLogin() //Verifing Login for username 'administrator' and password 'password'
		{		 	
			  try
			  {			   		
					String val1 =  tlogname.getText();
						   val1 =  val1.trim();
					String val2 =  (String)tlogpass.getText();
						   val2 =  val2.trim();	
					//JDBC step 1 :  Writing the query 		
					query1 = "SELECT * FROM login WHERE login='"+val1+"' AND passwd='"+val2+"'";
					//JDBC step 2 : Registering the driver="sun.jdbc.odbc.JdbcOdbcDriver"; for MSACCESS
					Class.forName(driver);
					//JDBC step 3 : Using the driver="sun.jdbc.odbc.JdbcOdbcDriver";
			   		Connection con=DriverManager.getConnection("jdbc:odbc:student");
					//JDBC step 4 : Creating a statement 	
			   		Statement st = con.createStatement();
					//JDBC step 5 : Exceuting the query 
					boolean bresults = st.execute(query1);
					//JDBC step 6 : Checking the results if(bresults)
					{
			    		ResultSet result = st.getResultSet();
						//JDBC step 7 : if there are results retrieve the SET
			    		if(result!=null)
						{ 	
							//Calling the method "showResults(ResultSet r)" and pass ResultSet to it 
							showResults(result);
			   			}
			   			else
			   			{
				   			
			   			}
						//JDBC step 8 : Closing the database connection 
						//con.close();
			  		}				
			  }
			  catch(SQLException ex)
			   {
			    System.out.println("Unable to access the database");
			   }
			  catch(ClassNotFoundException ex)
			   {
			    System.out.println("Class not found");
			   }
			  catch(Exception ex)
			  {
               System.out.println("Exception raised is:"+ex);
			  }
		}	
		//--------------------------------------------Ending with verifyLogin() method-------
		
		//--------------------------------------------Starting with showResults() method------
		void showResults(ResultSet r) throws SQLException 
		{
  				ResultSetMetaData rmeta = r.getMetaData();
				//Retrieving the Metadata from the resultset 
				int frecord = 0;
  				int nCols=rmeta.getColumnCount();
				if(r.next())
				{		
						// Retrieving the first field from the login table of the student.mdb database
						String param1 = r.getString(1).trim();
						String param2 = r.getString(2).trim();
						if (param1.equals("administrator") && param2.equals("password")) 
						{
							// Verifying for login "administrator" and password 'password' 
						    // if found record, then set frecord = 1
						    frecord = 1;
							//removing Login TAB
							tbPane.removeTabAt(0);
							// Displaying other panes 
							displayPanes();	
						}						
 						/*String param2 = r.getString(2).trim();
						if (param2.equals("password")) 
						{
							// Verifying for login "administrator" and password 'password' 
						    // if found record, then set frecord = 1
						    frecord = 1;
							//removing Login TAB
							tbPane.removeTabAt(0);
							// Displaying other panes 
							displayPanes();	
						}	*/					
						else if(frecord==0)
						{
						//if frecord is zero, then display invalid login.
						message_dialog = "Invalid Login name/password. Please enter again. ";
						type_dialog = JOptionPane.INFORMATION_MESSAGE;
						//Disaplaying the alert message
						JOptionPane.showMessageDialog((Component) null, message_dialog, fail_dialog, type_dialog);
						//Creating the tlogname and tlogpass textboxes empty
						tlogname.setText(" ");
						tlogpass.setText(" ");
						}	
				}
			else
			{
						message_dialog = "Invalid Login. Please Enter Again.";
						type_dialog = JOptionPane.INFORMATION_MESSAGE;
						JOptionPane.showMessageDialog((Component) null, message_dialog, fail_dialog, type_dialog);
						tlogname.setText(" ");
						tlogpass.setText(" ");
			}
		}	
		//------------------------------------------------------------------- Ending with showResults() method -------	
		
		//=================================================================== Starting with addRecord() method====
		void addRecord() 
		{
		 	  try
			  {			  
					Class.forName(driver);					
			   		Connection con=DriverManager.getConnection(url);
			   		Statement st = con.createStatement();	
					//Generating new register number
					String query2 = "SELECT * FROM student_details";
					ResultSet rs = st.executeQuery(query2);
					int cnt = 0;
					while (rs.next()) 
					{
    					cnt++;      					
					}
					cnt = cnt+1;
					String cs = (String)course.substring(0,3);
					char ss = year.charAt(0);
					reg_no = ""+cs+""+ss+"-"+cnt;
				query3 = "INSERT INTO student_details (name,preaddress,permaaddress,fathername,dateofbirth,fatheroccupation,phone,gender,year,course,dmc,degree,charcerti,ncccerti) VALUES ('"+tsname.getText()+"','"+tspreaddress.getText()+"','"+tspermaaddress.getText()+"','"+tsfathername.getText()+"','"+tsdob.getText()+"','"+foccupation+"','"+tsphone.getText()+"','"+gender+"','"+year+"','"+course+"',"+dmcsel+","+degreesel+","+charcfsel+","+ncccfsel+")";
				int rs_int=st.executeUpdate(query3);	
					con.close();	
					message_dialog = " "+tsname.getText().toUpperCase()+" Record is Added ";
					type_dialog = JOptionPane.INFORMATION_MESSAGE;
					JOptionPane.showMessageDialog((Component) null, message_dialog, add_dialog, type_dialog);
					// Adding the new record to the JTable								
					String [] data=new String[7];
					data[0] = reg_no;		
					data[1] = tsname.getText();	
					data[2] = tspreaddress.getText();		
					data[3] = tspermaaddress.getText();
					data[4] = tsphone.getText();
					data[5] = tsfathername.getText();
					data[6] = tsdob.getText();	 
					defaulttablemodel.addRow(data);
					cb_fatheroccupation.setSelectedIndex(0);
					cb_course.setSelectedIndex(0); 
					cb_year.setSelectedIndex(0);   
					String snametemp = tsname.getText();
					cb_sname.addItem(tsname.getText());
					//Adding name to combobox in modify names TAB
					
					//Setting other textboxes as blank to enable new record entry
					tsname.setText(" ");
					tspreaddress.setText(" ");
					tsfathername.setText(" ");
										
					// Uncheck the checkboxes, if they are checked while adding data
					dmc.setSelected(false);
					degree.setSelected(false);
					ncc.setSelected(false);
					charcerti.setSelected(false);
					
					//clicking the radiobutton male , to make it default again
					male.doClick();		
				  }
			  catch(SQLException ex)
			   {
			    System.out.println("Unable to access the database");
			   }
			  catch(ClassNotFoundException ex)
			   {
			    System.out.println("Class not found");
			   }
			  catch(Exception ex)
			  {
               System.out.println("Exception raised is:"+ex);
			  }
		}
		//------------------------------------------------------------------- Ending with addRecord() -------	
		//=============================================================== Starting with accessModifiedDatabase() ========	
		void accessModifiedDatabase(int nsel) 
		{		 	
			  try
			  {		
					query4 = "SELECT * FROM student_details WHERE registration_no="+nsel+"";
					Class.forName(driver);
			   		Connection con=DriverManager.getConnection(url);
			   		Statement st = con.createStatement();
			   		boolean bresults = st.execute(query4);
			   		if(bresults)
					{
			    		ResultSet result = st.getResultSet();
			    		if(result!=null)
						{ 	
							showModifiedResults(result);
			   			}
						con.close();
			  		}						
			  }
			  catch(SQLException ex)
			   {
			    System.out.println("Unable to access the database");
			   }
			  catch(ClassNotFoundException ex)
			   {
			    System.out.println("Class not found");
			   }
			  catch(Exception ex)
			  {
               System.out.println("Exception raised is:"+ex);
			  }
		}	
		//------------------------------------------------------------------- Ending with accessModifiedDatabase() -------	
		//=============================================================== Starting with showModifiedResults()  ========	
		
		void showModifiedResults(ResultSet r) throws SQLException 
		{
  				ResultSetMetaData rmeta = r.getMetaData();
				int frecord = 0;
  				int nCols=rmeta.getColumnCount();
				String txt1="";
  				String txt2="";	
				String txt3="";	
				String txt4="";	
				String txt5="";	
				String txt6="";	
				String txt7="";	
				String txt8="";	
				String txt9="";
				String txt10="";
				String txt11="";
				int int9=0;
				int int10=0;
				int int11=0;
				int int12=0;
				while(r.next())
				{
					reg_no=r.getString(1);
  					txt2+=r.getString(2);
					txt3+=r.getString(3);
					txt4+=r.getString(4);
					txt5+=r.getString(5);
					txt6+=r.getString(6);
					txt7+=r.getString(7);
					txt8+=r.getString(8);
					txt9+=r.getString(9);
					txt10=r.getString(10);
					txt11=r.getString(11);
					int9=r.getInt(12);
					int10=r.getInt(13);
					int11=r.getInt(14);
					int12=r.getInt(15);		
				}
				currentname = txt2;				
				tmname.setText(txt2);
				tmpreaddress.setText(txt3);
				tmpermaaddress.setText(txt4);
				tmphone.setText(txt8);
				tmfathername.setText(txt5);				
				tmdob.setText(txt6);	
				txt7 = txt7.trim();
				if (txt7.equals("Govt. Employee ")) 
				{
					mcb_fatheroccupation.setSelectedIndex(0); 			
				}
				else if(txt7.equals("Self Employed"))
				{
					mcb_fatheroccupation.setSelectedIndex(1); 
				}				
				else
				{
					mcb_fatheroccupation.setSelectedIndex(2); 
				}
				txt9 = txt9.trim();
				if (txt9.equalsIgnoreCase("M")) 
				{
					m_male.doClick();						
				}
				else
				{
					m_female.doClick();
				}	
				
				txt11 = txt11.trim();
				if (txt11.equals("B.Sc.(Comp. Sc.)")) 
				{
					mcb_course.setSelectedIndex(1); 			
				}
				else
				{
					mcb_course.setSelectedIndex(0); 
				}				
				txt10 = txt10.trim();
				if (txt10.equals("I Year")) 
				{
					mcb_year.setSelectedIndex(0); 			
				}
				else if(txt10.equals("II Year"))
				{
					mcb_year.setSelectedIndex(1); 
				}				
				else
				{
					mcb_year.setSelectedIndex(2); 
				}		
				if(int9==1)
				{
					mdmc.setSelected(true);
				}
				else
				{
					mdmc.setSelected(false);
				}
				if(int10==1)
				{
					mdegree.setSelected(true);
				}
				else
				{
					mdegree.setSelected(false);
				}
				if(int11==1)
				{
					mcharcerti.setSelected(true);
				}
				else
				{
					mcharcerti.setSelected(false);
				}
				if(int12==1)
				{
					mncc.setSelected(true);
				}
				else
				{
					mncc.setSelected(false);
				}
		}
		//------------------------------------------------------------------- Ending with  showModifiedResults() -------	
		//=============================================================== Starting with accessStudName()  ========	
		
	void accessStudName() //Retrieving the student list in the combo box
		{
		 	  try
			  {			  		
					query5 = "SELECT registration_no,Name FROM student_details ORDER BY registration_no"; 
					Class.forName(driver);
			   		Connection con=DriverManager.getConnection(url);
			   		Statement st = con.createStatement();
					boolean bresults = st.execute(query5);
					if(bresults)
					{
			    		ResultSet result = st.getResultSet();
			    		if(result!=null)
						{ 							
							showStudName(result);
						}						
			   			con.close();
			  		}
				
			  }
			  catch(SQLException ex)
			   {
			    System.out.println("Unable to access the database");
			   }
			  catch(ClassNotFoundException ex)
			   {
			    System.out.println("Class not found");
			   }
			  catch(Exception ex)
			  {
               System.out.println("Exception raised is:"+ex);
			  }
		}
		//------------------------------------------------------------------- Ending with  accessStudName() -------	
		//=============================================================== Starting with showStudName()  ========	
				
		void showStudName(ResultSet r) throws SQLException 
		{
  				ResultSetMetaData rmeta = r.getMetaData();
  				int nCols=rmeta.getColumnCount(); 
  				while(r.next())
				{	
					cb_sname.addItem(r.getString(1)+":"+r.getString(2));
					ctr_val++;					
				}	
		}
		//------------------------------------------------------------------- Ending with  showStudName() -------	
		//=============================================================== Starting with deleteRecord()  ========	
				
		void deleteRecord(int nsel) //Deleting the record for the selected student name
		{		 	
			  try
			  {					  					
					Class.forName(driver);					
			   		Connection con=DriverManager.getConnection(url);
			   		Statement st = con.createStatement();	
			   		System.out.println("Delete name is :=="+nsel);
					query6 = "DELETE FROM student_details WHERE registration_no="+nsel;
					st.executeUpdate(query6);					
					con.close();			
					mcb_course.setSelectedIndex(0);		 
					mcb_year.setSelectedIndex(0);			 
					tmname.setText(" ");		
					tmdob.setText(" ");			
					tmpreaddress.setText(" ");			
					tmpermaaddress.setText(" ");	
					tmfathername.setText(" ");			
					tmphone.setText(" ");
					mdmc.setSelected(false);			
					mdegree.setSelected(false);			
					mncc.setSelected(false);		
					mcharcerti.setSelected(false);		
					m_male.doClick();						
			  }
			  catch(SQLException ex)
			   {
			    System.out.println("Unable to access the database");
			   }
			  catch(ClassNotFoundException ex)
			   {
			    System.out.println("Class not found");
			   }
			  catch(Exception ex)
			  {
               System.out.println("Exception raised is:"+ex);
			  }
	}	
		//------------------------------------------------------------------- Ending with  deleteRecord() -------	
		//=============================================================== Starting with updateRecord()  ========	

		void updateRecord(int std_id,String str_name) //Update Database when modified
		{
		 	  try
			  {
			  		query7 = "UPDATE student_details SET name='"+tmname.getText()+"', preaddress='"+tmpreaddress.getText()+"', permaaddress='"+tmpermaaddress.getText()+"', fathername='"+tmfathername.getText()+"', dateofbirth='"+tmdob.getText()+"', fatheroccupation='"+foccupation+"', phone='"+tmphone.getText()+"', gender='"+gender+"', year='"+year+"', course='"+course+"', dmc="+dmcsel+", degree="+degreesel+", charcerti="+charcfsel+", ncccerti="+ncccfsel+" WHERE registration_no="+std_id; 
					Class.forName(driver);
			   		Connection con=DriverManager.getConnection(url);
			   		Statement st = con.createStatement();
					System.out.println(query7);
			   		int ret=st.executeUpdate(query7);	
			   		System.out.println("Output is "+ret);
		   			con.close();
		   			System.out.println("Called After"+str_name);
		   			message_dialog = " "+tmname.getText().toUpperCase()+" Record is Updated ";
					type_dialog = JOptionPane.INFORMATION_MESSAGE;
					JOptionPane.showMessageDialog((Component) null, message_dialog, modify_dialog, type_dialog);
			  }
			  catch(SQLException ex)
			   {
			    System.out.println("Unable to access the database");
			   }
			  catch(ClassNotFoundException ex)
			   {
			    System.out.println("Class not found");
			   }
			  catch(Exception ex)
			  {
               System.out.println("Exception raised is:"+ex);
			  }			  
		}		
	//------------------------------------------------------------------- Ending with  deleteRecord() -------	
	//=============================================================== Starting with ButtonHandler class  ========	
	
	class ButtonHandler implements ActionListener 
		{
  			public void actionPerformed(ActionEvent ev)
			{
					
   				String s=ev.getActionCommand(); 	
				if(s.equals("Click to Continue"))
				{
					cont.setEnabled(false);
					displayPanel1();
					tbPane.setSelectedIndex(1);
					tbPane.removeTabAt(0);
				}
   				else if(s=="LOGIN") //Login is Clicked
				{
							
					verifyLogin();					
					tbPane.setSelectedIndex(0);
					tlogname.setText("");
				}  
				else if(s.equals("SAVE RECORD")) //Save Record is Clicked
				{
					
					addRecord();
					String snametemp = tsname.getText();
				}	
				else if(s.equals("MODIFY")) //Modify Record is Clicked
				{					
					int student_id=Integer.parseInt(selname.substring(0,2));
					updateRecord(student_id,selname);
					int x=ctr_val;
					
					for(int y=x;y>1;y--)
					{
						cb_sname.removeItemAt(y);
						
				    }
					cb_sname.addItem("Select Student Name");
				    cb_sname.removeItemAt(0);
				    cb_sname.removeItemAt(1);
				    accessStudName();
				    cb_sname.removeItemAt(1);
				   				
				}
				else if(s.equals("DELETE"))	//Delete Record Button is Clicked
				{
					dlgCPane.setLayout(new FlowLayout());					
					dlgCPane.add(jLabeldlg);
					jLabeldlg.setFont(tFont);
					dlgCPane.add(delit);
					delit.addActionListener(new ButtonHandler());		
					dlgCPane.add(delno);
					delno.addActionListener(new ButtonHandler());
					dlg.setBounds(300, 200, 250, 120);
					dlg.show();
				}
				else if(s.equals("YES"))	//Yes button is clicked in Delete dialog box
				{
					dlg.dispose();
					selname = cb_sname.getSelectedItem().toString();
					int student_id=Integer.parseInt(selname.substring(0,2));
  					deleteRecord(student_id);
  					int x=ctr_val;
					for(int y=x;y>1;y--)
					{
						cb_sname.removeItemAt(y);
				    }
					cb_sname.addItem("Select Student Name");
				    cb_sname.removeItemAt(0);
				    cb_sname.removeItemAt(1);
				    accessStudName();
				    cb_sname.removeItemAt(1);
				}
				
				else if(s.equals("NO"))		//No button is clicked in the Delete dialog box
				{
					dlg.dispose();
				}								
			}
 		}
		//------------------------------------------------------------- Ending with the ButtonHandler --------	
		//=========================================================== Starting with the MenuItemHandler ======
 		
		class MenuItemHandler implements ActionListener 
		{
  			public void actionPerformed(ActionEvent ev)
			{
   				String s=ev.getActionCommand();
   				if(s=="Exit")
				{
					//close window on menuitem exit
    				System.exit(0);
   				}								
  			}					
 		}
		//----------------------------------------------------------- Ending with the MenuItemHandler ------
		//=========================================================== Starting with the WindowEventHandler ===
 		class WindowEventHandler extends WindowAdapter 
		{
  			public void windowClosing(WindowEvent e)
			{
				//Closing the window on exit
   				System.exit(0);
  			}
 		}
		//----------------------------------------------------------- Ending with WindowEventHandler ---
		//=========================================================== Starting with RadioListener ========
		
		class RadioListener implements ActionListener 
		{
      		public void actionPerformed(ActionEvent e) 
			{
				  gender = e.getActionCommand();
				 
				  condsel = e.getActionCommand();
				  
		    }
		}
		//----------------------------------------------------------- Ending with the RadioListener --------
		//=========================================================== Starting with the ComboBoxListener ========
		
		class ComboBoxListener implements ItemListener  
		{
			public ComboBoxListener()
			{
				super();
			}
      		public void itemStateChanged(ItemEvent e) 
			{				
		    	str = (String)e.getItem();
				if (e.getSource().equals(cb_fatheroccupation))
    			{
    				foccupation = cb_fatheroccupation.getSelectedItem().toString();
    			}
				if (e.getSource().equals(mcb_fatheroccupation))
    			{
    				foccupation = mcb_fatheroccupation.getSelectedItem().toString();
    			}
				if (e.getSource().equals(cb_course))
    			{
    				course = cb_course.getSelectedItem().toString();
    			}
				if (e.getSource().equals(mcb_course))
    			{
    				course = mcb_course.getSelectedItem().toString();
    			}
    			if (e.getSource().equals(cb_year))
    			{
    				year = cb_year.getSelectedItem().toString();
    			}
				if (e.getSource().equals(mcb_year))
    			{
    				year = mcb_year.getSelectedItem().toString();
    			}
				if (e.getSource().equals(cb_sname))
    			{	
	    
					selname = cb_sname.getSelectedItem().toString();
  					accessModifiedDatabase(Integer.parseInt(selname.substring(0,2)));					
    			}
				if (e.getSource().equals(jstudID))
    			{
					studID = jstudID.getSelectedItem().toString();
    			}
				if (e.getSource().equals(jeq))
    			{
					eq = jeq.getSelectedItem().toString();
    			}
		    }
		}
		//----------------------------------------------------------- Ending with the ComboBoxListener --------
		//=========================================================== Starting with the CheckBoxListener =====		
		
		class CheckBoxListener implements ItemListener 
		{
    		
			public void itemStateChanged(ItemEvent e) 
    		{
        		Object source = e.getItemSelectable();
				if ((e.getStateChange() == ItemEvent.SELECTED) && (source==dmc))
       			{
					dmcsel = 1;
      			}
				if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==dmc))
       			{
					dmcsel = 0;
      			}					
				if ((e.getStateChange() == ItemEvent.SELECTED)  && (source==degree))
       			{
					degreesel = 1;
      			}
				if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==degree))
       			{
					degreesel = 0;
      			}					
				if ((e.getStateChange() == ItemEvent.SELECTED) && (source==ncc))
       			{
					ncccfsel = 1;
      			}
				if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==ncc))
       			{
					ncccfsel = 0;
      			}					
				if ((e.getStateChange() == ItemEvent.SELECTED)  && (source==charcerti))
       			{
					charcfsel = 1;
      			}
				if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==charcerti))
       			{
					charcfsel = 0;
      			}						
				if ((e.getStateChange() == ItemEvent.SELECTED) && (source==mdmc))
       			{
					mdmcsel = 1;
      			}
				if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==mdmc))
       			{
					mdmcsel = 0;
      			}					
				if ((e.getStateChange() == ItemEvent.SELECTED)  && (source==mdegree))
       			{
					mdegreesel = 1;
      			}
				if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==mdegree))
       			{
					mdegreesel = 0;
      			}					
				if ((e.getStateChange() == ItemEvent.SELECTED) && (source==mncc))
       			{
					mnccsel = 1;
      			}
				if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==mncc))
       			{
					mnccsel = 0;
      			}					
				if ((e.getStateChange() == ItemEvent.SELECTED)  && (source==mcharcerti))
       			{
					mcharcfsel = 1;
      			}
				if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==mcharcerti))
       			{
					mcharcfsel = 0;
      			}		
				if ((e.getStateChange() == ItemEvent.SELECTED) && (source==ckstudentID))
       			{
					selstudentID = 1;
      			}
				if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==ckstudentID))
       			{
					selstudentID = 0;
      			}	
				if ((e.getStateChange() == ItemEvent.SELECTED) && (source==ckstudentname))
       			{
					selstudentname = 1;
      			}
				if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==ckstudentname))
       			{
					selstudentname = 0;
      			}	
				if ((e.getStateChange() == ItemEvent.SELECTED) && (source==ckpreaddress))
       			{
					selstudentpreadd = 1;
      			}
				if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==ckpreaddress))
       			{
					selstudentpreadd = 0;
      			}	
      			if ((e.getStateChange() == ItemEvent.SELECTED) && (source==ckpermaaddress))
       			{
					selstudentpermaadd = 1;
      			}
      			if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==ckpermaaddress))
       			{
					selstudentpermaadd = 0;
      			}	
				if ((e.getStateChange() == ItemEvent.SELECTED) && (source==ckfathername))
       			{
					selfathername = 1;
      			}
				if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==ckfathername))
       			{
					selfathername = 0;
      			}	
				if ((e.getStateChange() == ItemEvent.SELECTED) && (source==ckgender))
       			{
					selgender = 1;
      			}
				if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==ckgender))
       			{
					selgender = 0;
      			}	
				if ((e.getStateChange() == ItemEvent.SELECTED) && (source==ckdob))
       			{
					seldob = 1;
      			}
				if ((e.getStateChange() == ItemEvent.DESELECTED)  && (source==ckdob))
       			{
					seldob = 0;
      			}	
										
    		}			
		}
}
