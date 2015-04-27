package jmeldahl2740ex3g;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;

import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class PayrollForm extends JFrame {

	private JPanel contentPane;
	private JList employeeList;
	private JTextField hoursTextField;
	private JLabel totalHoursLabel;
	private DefaultListModel employeeListModel;
	private JButton clearFormButton;
	private JLabel grossPayLabel;
	private JTextField empIdTextField;
	private JTextField empNameTextField;
	private JTextField payRateTextField;
	private JButton updateButton;
	private JButton addHoursButton;
	private JButton clearHoursButton;
	private PayrollObjMapper payrollObjMapper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PayrollForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
		setTitle("JMeldahl 2740 Ex3G");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectEmployee = new JLabel("Select employee:");
		lblSelectEmployee.setBounds(10, 11, 107, 14);
		contentPane.add(lblSelectEmployee);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 36, 343, 105);
		contentPane.add(scrollPane);
		
//		employeeList = new JList();
//		employeeListModel = new DefaultListModel();
//		employeeListModel.addElement(new Payroll(101, "Jessica Meldahl", 10.0));
//		employeeListModel.addElement(new Payroll(102, "Patti Weigard", 20.0));
//		employeeListModel.addElement(new Payroll(103, "Lyle Stelter", 30.0));
//		employeeListModel.addElement(new Payroll(104, "Neva Burdick", 40.0));
//		employeeListModel.addElement(new Payroll(105, "Lisa Laing", 50.0));
		payrollObjMapper = new PayrollObjMapper("exercise3g.txt");
		employeeListModel = payrollObjMapper.getAllPayroll();
		
		employeeList = new JList(employeeListModel);
		employeeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				do_employeeList_mouseClicked(arg0);
			}
		});
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(employeeList);
		
		JLabel lblNewLabel = new JLabel("Employee ID (> 100):");
		lblNewLabel.setBounds(20, 167, 128, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmployeeName = new JLabel("Employee Name:");
		lblEmployeeName.setBounds(20, 192, 107, 14);
		contentPane.add(lblEmployeeName);
		
		JLabel lblPayRate = new JLabel("Pay Rate (7.25 - 100):");
		lblPayRate.setBounds(20, 217, 128, 14);
		contentPane.add(lblPayRate);
		
		JLabel lblEnterHours = new JLabel("Enter Hours (0.1 - 20.0):");
		lblEnterHours.setBounds(20, 242, 150, 14);
		contentPane.add(lblEnterHours);
		
		hoursTextField = new JTextField();
		hoursTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				do_hoursTextField_focusGained(e);
			}
		});
		hoursTextField.setText("0.00");
		hoursTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		hoursTextField.setBounds(180, 239, 70, 20);
		contentPane.add(hoursTextField);
		hoursTextField.setColumns(10);
		
		addHoursButton = new JButton("+");
		addHoursButton.setEnabled(false);
		addHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_addHoursButton_actionPerformed(arg0);
			}
		});
		addHoursButton.setBounds(260, 238, 41, 23);
		contentPane.add(addHoursButton);
		
		clearHoursButton = new JButton("Clear");
		clearHoursButton.setEnabled(false);
		clearHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_clearHoursButton_actionPerformed(e);
			}
		});
		clearHoursButton.setBounds(304, 238, 70, 23);
		contentPane.add(clearHoursButton);
		
		JLabel lblTotalHours = new JLabel("Total Hours:");
		lblTotalHours.setBounds(20, 267, 97, 14);
		contentPane.add(lblTotalHours);
		
		totalHoursLabel = new JLabel("0.00");
		totalHoursLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalHoursLabel.setBounds(180, 270, 70, 14);
		contentPane.add(totalHoursLabel);
		
		JLabel lblGrossPay = new JLabel("Gross Pay:");
		lblGrossPay.setBounds(20, 292, 97, 14);
		contentPane.add(lblGrossPay);
		
		clearFormButton = new JButton("Clear Form");
		clearFormButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_clearFormButton_actionPerformed(e);
			}
		});
		clearFormButton.setBounds(260, 317, 114, 23);
		contentPane.add(clearFormButton);
		
		grossPayLabel = new JLabel("$0.00");
		grossPayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		grossPayLabel.setBounds(180, 292, 70, 14);
		contentPane.add(grossPayLabel);
		
		empIdTextField = new JTextField();
		empIdTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		empIdTextField.setText("000");
		empIdTextField.setBounds(180, 164, 70, 20);
		contentPane.add(empIdTextField);
		empIdTextField.setColumns(10);
		
		empNameTextField = new JTextField();
		empNameTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		empNameTextField.setBounds(137, 189, 113, 20);
		contentPane.add(empNameTextField);
		empNameTextField.setColumns(10);
		
		payRateTextField = new JTextField();
		payRateTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		payRateTextField.setText("0.00");
		payRateTextField.setBounds(180, 214, 70, 20);
		contentPane.add(payRateTextField);
		payRateTextField.setColumns(10);
		
		updateButton = new JButton("Update");
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_updateButton_actionPerformed(arg0);
			}
		});
		updateButton.setBounds(161, 317, 89, 23);
		contentPane.add(updateButton);
	}
	
	protected void do_employeeList_mouseClicked(MouseEvent arg0) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		this.empIdTextField.setText(Integer.toString(payroll.getId()));
		this.empNameTextField.setText(payroll.getName());
		DecimalFormat dollarFmt = new DecimalFormat("#,###.00");
		this.payRateTextField.setText(dollarFmt.format(payroll.getPayRate()));
		DecimalFormat hoursFmt = new DecimalFormat("##0.00");
		this.totalHoursLabel.setText(hoursFmt.format(payroll.getHours()));
		this.grossPayLabel.setText(dollarFmt.format(payroll.calcGrossPay()));
		this.hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
		this.addHoursButton.setEnabled(true);
		this.clearHoursButton.setEnabled(true);
		this.updateButton.setEnabled(true);
	}
	
	protected void do_addHoursButton_actionPerformed(ActionEvent arg0) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		double hours = Double.parseDouble(this.hoursTextField.getText());
		
		if (payroll.addHours(hours)) {
			DecimalFormat hoursFmt = new DecimalFormat("##0.00");
			this.totalHoursLabel.setText(hoursFmt.format(payroll.getHours()));
			DecimalFormat dollarFmt = new DecimalFormat("#,##0.00");
			this.grossPayLabel.setText(dollarFmt.format(payroll.calcGrossPay()));
			this.hoursTextField.setText("0.00");
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalid hours. \nMust be 0.1 - 20.0.");
		}
		this.hoursTextField.requestFocus();
	}
	
	protected void do_clearHoursButton_actionPerformed(ActionEvent e) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		payroll.setHours(0.00);
		this.totalHoursLabel.setText("0.00");
		this.grossPayLabel.setText("$0.00");
		this.hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
	}
	
	protected void do_clearFormButton_actionPerformed(ActionEvent e) {
		this.empIdTextField.setText("0");
		this.empNameTextField.setText("");
		this.payRateTextField.setText("0.00");
		this.totalHoursLabel.setText("0.00");
		this.grossPayLabel.setText("$0.00");
		this.hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
		this.employeeList.clearSelection();
		this.addHoursButton.setEnabled(false);
		this.clearHoursButton.setEnabled(false);
		this.updateButton.setEnabled(false);
	}
	
	protected void do_hoursTextField_focusGained(FocusEvent e) {
		hoursTextField.selectAll();
	}
	
	protected void do_updateButton_actionPerformed(ActionEvent arg0) {	
		int id = Integer.parseInt(empIdTextField.getText());
		double payRate = Double.parseDouble(payRateTextField.getText());
		String name = empNameTextField.getText();
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		
		if (!payroll.setId(id)) {
			JOptionPane.showMessageDialog(null, "Invalid employee ID. \nMust be > 100.");
			empIdTextField.setText(Integer.toString(payroll.getId()));
			empIdTextField.requestFocus();
		}
		else {
			if (!payroll.setName(empNameTextField.getText())) {
				JOptionPane.showMessageDialog(null, "Invalid name. \nName is required.");
				this.empNameTextField.setText(payroll.getName());
				this.empNameTextField.requestFocus();
			}
			else {
				if (!payroll.setPayRate(payRate)) {
					JOptionPane.showMessageDialog(null, "Invalid pay rate. \nMust be 7.25 - 100.00.");
					DecimalFormat rateFmt = new DecimalFormat("#,###.00");
					this.payRateTextField.setText(rateFmt.format(payroll.getPayRate()));
					this.payRateTextField.requestFocus();
						}
							else {
								payroll.setId(id);
								payroll.setName(name);
								payroll.setPayRate(payRate);
							}
					}
			}
			
	employeeList.repaint();
	}
	
	protected void do_this_windowClosing(WindowEvent arg0) {
		if (payrollObjMapper != null)
			payrollObjMapper.writeAllPayroll(employeeListModel);
	}
}
