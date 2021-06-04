package com.gft.loja.domain.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.gft.loja.domain.model.ItensVenda;
import com.gft.loja.domain.model.Venda;

@Service
public class SenderMailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String emailFrom;
	
	
	
	@Async
	public void enviar(Venda venda) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		String emailCliente = venda.getCliente().getEmail();
		String mensagem = "";
		BigDecimal total = BigDecimal.ZERO;
		List<ItensVenda> itensVenda = venda.getItensVenda();
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message,true);
		
		
		String assuntoEmail = "Recibo de compra";
		helper.setSubject(assuntoEmail);
		mensagem += "<span style='font-size:40px'>Olá, obrigado pela compra!</span><br>"
				+ "<b style='font-size:30px'>Recibo: </b><br>"
				+ "<div style='display:flex;justity-content:center; font-size:20px;color:black'><table style='width:700px;text-align:center;'><thead><tr>"
				+ "<th>Quantidade</th><th>Descrição</th><th>Preço unitário</th><th>Valor</th>"
				+ "</tr></thead>"; //Header da tabela do email
		
		mensagem += "<tbody>";
		for(ItensVenda i : itensVenda) {
			mensagem += "<tr><td>"+ i.getQuantidade() +"</td><td>" 
		+ i.getItensVendaPK().getProduto().getDescricao()+"</td><td>" //Itens do recibo
		+"R$ " + df.format(i.getValorVenda().divide(new BigDecimal(i.getQuantidade()))) + "</td><td>"+"R$ " + df.format(i.getValorVenda()) + "</td></tr>";
		total = total.add(i.getValorVenda());
		}
		
		mensagem += "</tbody></table></div>" + "<br>" + "<span style='font-size:30px'>Total:" +"R$ " + df.format(total) +"</span>";

		helper.setText(mensagem,true);
		helper.setFrom(emailFrom);
		helper.setTo(emailCliente);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}
}
