package agenda.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import agenda.controller.util.MyHttpServletRequestWrapper;
import agenda.model.DAO;
import agenda.model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** DAO. */
	DAO dao = new DAO();

	/** JavaBeans */
	JavaBeans contato = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyHttpServletRequestWrapper hsrw = new MyHttpServletRequestWrapper(request);
		String action = ((HttpServletRequest) hsrw.getRequest()).getServletPath();
		System.out.println("Resource=" + action);
		System.out.println(hsrw.toString());

		switch (action) {
		case "/main":
			contatos(request, response);
			break;
		case "/insert":
			adicionarContato(request, response);
			break;
		case "/select":
			obterContato(request, response);
			break;
		case "/update":
			editarContato(request, response);
			break;
		case "/delete":
			deletarContato(request, response);
			break;
		case "/report":
			gerarRelatorio(request, response);
			break;
		default:
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Contatos.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		printResourceMethod();

		ArrayList<JavaBeans> contatos = dao.obterContatos();

		System.out.println("Listagem de contatos");
		for (JavaBeans contato : contatos) {
			System.out.println(contato);
		}
		System.out.println();

		request.setAttribute("contatos", contatos);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * Adicionar contato.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	protected void adicionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		printResourceMethod();

		String requestData[] = { "nome", "fone", "email" };

		contato.setNome(request.getParameter(requestData[0]));
		contato.setFone(request.getParameter(requestData[1]));
		contato.setEmail(request.getParameter(requestData[2]));

		dao.inserirContato(contato);
		System.out.println(contato + "\n");

		response.sendRedirect("main");
	}

	/**
	 * Obter contato.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws IOException      Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	protected void obterContato(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		printResourceMethod();
		String idcon = request.getParameter("idcon");
		contato.setIdcon(idcon);
		dao.selecionarContato(contato);
		System.out.println(contato + "\n");
		request.setAttribute("contato", contato);
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar contato.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws IOException {
		printResourceMethod();
		String requestData[] = { "nome", "fone", "email" };

		contato.setNome(request.getParameter(requestData[0]));
		contato.setFone(request.getParameter(requestData[1]));
		contato.setEmail(request.getParameter(requestData[2]));

		dao.editarContato(contato);
		System.out.println(contato + "\n");

		response.sendRedirect("main");
	}

	/**
	 * Deletar contato.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void deletarContato(HttpServletRequest request, HttpServletResponse response) throws IOException {
		printResourceMethod();
		contato.setIdcon(request.getParameter("idcon"));
		dao.deletarContato(contato);
		response.sendRedirect("main");
	}

	/**
	 * Gerar relatorio.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws IOException {
		printResourceMethod();
		ArrayList<JavaBeans> contatos = dao.obterContatos();
		String nome = "contatos.pdf";

		Document doc = new Document();
		try {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline; filename=" + nome);
			PdfWriter.getInstance(doc, response.getOutputStream());

			doc.open();
			doc.add(new Paragraph("Lista de contatos: "));
			doc.add(new Paragraph(" "));

			PdfPTable table = new PdfPTable(3);
			String[] cols = { "Nome", "Fone", "Email" };

			for (String col : cols) {
				table.addCell(new PdfPCell(new Paragraph(col)));
			}

			for (JavaBeans contato : contatos) {
				table.addCell(new PdfPCell(new Paragraph(contato.getNome())));
				table.addCell(new PdfPCell(new Paragraph(contato.getFone())));
				table.addCell(new PdfPCell(new Paragraph(contato.getEmail())));
			}

			doc.add(table);
			doc.close();
		} catch (Exception e) {
			System.out.println(e);
			doc.close();
		}
	}

	/**
	 * Prints the resource method.
	 */
	private void printResourceMethod() {
		String methodName = new Throwable().getStackTrace()[1].getMethodName();
		String className = getClass().getSimpleName();
		System.out.println(methodName + "@" + className);
	}
}
