package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controllers.NovoEventoController;
import core.Model;
import core.View;
import model.Evento;
import model.EventoUtil;
import model.Frequencia;

public class NovoEventoView extends JPanel implements View {

	private NovoEventoController novoEventoController;
	private JTextField textoDescricao;
	private JTextField textoEmail;
	private JFormattedTextField date;
	private JCheckBox checkAlarme;
	private JRadioButton radioDiario;
	private JRadioButton radioSemanal;
	private JRadioButton radioMensal;
	
	public NovoEventoView(NovoEventoController novoEventoController) {
		
		this.novoEventoController = novoEventoController;
		
		makeFrame();
		makeCampoDescricao();
		makeCampoEmail();
		makeCampoData();
		makeCampoFrequencia();
		makeAlarme();
		buttonSalvar();
		makeLimparDados();
		
	}
	
	public void makeFrame() {
		setLayout(null);
	}
	
	public void makeCampoDescricao() {
		
		//label ou rótulo
		JLabel label = new JLabel("Descrição do Evento");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(29, 29, 134, 14);
		add(label);
		
		//campo
		textoDescricao = new JTextField();
		textoDescricao.setBounds(169, 26, 196, 20);
		add(textoDescricao);
		textoDescricao.setColumns(10);
	}
	
	public void makeCampoEmail() {
		
		//label
		JLabel label = new JLabel("Email para Envio");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(29, 71, 104, 14);
		add(label);
		
		//campo
		textoEmail = new JTextField();
		textoEmail.setBounds(169, 68, 196, 20);
		add(textoEmail);
		textoEmail.setColumns(10);
		
	}
	
	public void makeCampoData() {
		
		//label
		JLabel label = new JLabel("Data");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(29, 119, 78, 14);
		add(label);
		
		
		//campo
		try {
			
			date = new JFormattedTextField(new MaskFormatter("##/##/####"));
			date.setBounds(169, 116, 96, 20);
			add(date);
			date.setColumns(10);
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public void makeCampoFrequencia() {
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		//label frequencia
		JLabel frequencia = new JLabel("Frequência");
		frequencia.setFont(new Font("Tahoma", Font.BOLD, 11));
		frequencia.setBounds(29, 164, 78, 14);
		add(frequencia);
		
		// campo opção diário
		radioDiario = new JRadioButton("Diário");
		btnGroup.add(radioDiario);
		radioDiario.setSelected(true);
		radioDiario.setBounds(169, 160, 60, 23);
		add(radioDiario);
		
		// campo opção semanal
		radioSemanal = new JRadioButton("Semanal");
		btnGroup.add(radioSemanal);
		radioSemanal.setBounds(253, 160, 67, 23);
		add(radioSemanal);
		
		// campo opção mensal
		radioMensal = new JRadioButton("Mensal");
		btnGroup.add(radioMensal);
		radioMensal.setBounds(347, 160, 78, 23);
		add(radioMensal);
	
	}
	
	public void makeAlarme() {
		
		checkAlarme = new JCheckBox("Alarme");
		checkAlarme.setBounds(29, 220, 89, 23);
		add(checkAlarme);
	}
	
	public void buttonSalvar() {
		
		JButton botaoSalvar = new JButton("Salvar");
		botaoSalvar.setBounds(127, 220, 89, 23);
		add(botaoSalvar);
		
		botaoSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Evento evento = new Evento();
				
				evento.setDate(EventoUtil.getDateFromString(date.getText()));
				evento.setDescricaoEvento(textoDescricao.getText());
				evento.setAlarme(checkAlarme.isSelected() ? true : false);
				evento.setEmail(textoEmail.getText());
				
				if(radioDiario.isSelected())
					evento.setFrequencia(Frequencia.DIARIO);
				else if(radioSemanal.isSelected())
					evento.setFrequencia(Frequencia.SEMANAL);
				else
					evento.setFrequencia(Frequencia.MENSAL);
				
				novoEventoController.addEvento(evento);
				
				limparDados();
				
			}

			
			
		});
		
	}
	
	public void makeLimparDados() {
		
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(253, 220, 89, 23);
		add(botaoLimpar);
		
		botaoLimpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				limparDados();
			}
		});
		
	}
	
	public void limparDados() {
		
		textoDescricao.setText("");
		date.setText("");
		checkAlarme.setSelected(false);
		textoEmail.setText("");
		radioDiario.setSelected(true);
		
	}
	
	@Override
	public void update(Model model, Object data) {
		// TODO Auto-generated method stub
		//add
		if(data != null) {
			String notice = (String) data;
		}
		//add
	}

}
