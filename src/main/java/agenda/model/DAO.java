package agenda.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {

	/** Módulo de conexão *. */
	private String driver = "org.mariadb.jdbc.Driver";

	/** The url. */
	private String url = "jdbc:mariadb://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";

	/** The user. */
	private String user = "root";

	/** The password. */
	private String password = "";

	/**
	 * Instantiates a new DAO.
	 */
	public DAO() {
		super();
	}

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection conn = null;
		try {
			Class.forName(driver); // Não é mais necessário (Java1.6+), mas vincula a execução à existência do
									// driver no classpath.
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * CRUD CREATE.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(JavaBeans contato) {
		String query = "insert into contatos (nome, fone, email) values (?, ?, ?)";
		try {
			Connection conn = conectar();

			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.executeUpdate();

			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * CRUD READ.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> obterContatos() {
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String query = "select * from contatos order by nome";

		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);

				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}

			conn.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
		}

		return contatos;
	}

	/**
	 * CRUD UPDATE.
	 *
	 * @param contato the contato
	 */
	public void editarContato(JavaBeans contato) {
		String query = "update contatos set nome=?, fone=?, email=? where idcon=?";
		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			if (pst.executeUpdate() == 0)
				System.out.println("Contato editado");
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * CRUD DELETE.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		String query = "delete from contatos where idcon=?";
		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, contato.getIdcon());
			if (pst.executeUpdate() == 0)
				System.out.println("Contato apagado");
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	public void selecionarContato(JavaBeans contato) {
		String query = "select * from contatos where idcon=?";
		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			rs.next();
			contato.setNome(rs.getString(2));
			contato.setFone(rs.getString(3));
			contato.setEmail(rs.getString(4));
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Testar conexao.
	 */
	public void testarConexao() {
		try {
			Connection conn = conectar();
			System.out.println(conn);
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
