package repositorio;

import entidades.Professor;
import entidades.Estudante;
import java.sql.PreparedStatement; // Usado para criar um objeto que representa a instrução SQL que será executada.
import java.sql.Connection; // Representa uma conexão ao BD.
import java.sql.DriverManager; // Oferece métodos para gerenciar um driver JDBC.
import java.sql.SQLException; // Usado no tratamento de exceções.
import java.sql.ResultSet; // Representa o conjunto de resultados de uma tabela no BD.
import java.util.ArrayList;
import java.util.List;

public class InstituicaoDAO {

    private final String url = "jdbc:mysql://localhost:3306/projeto_a3_final"; // ''Endereço'' do BD.
    private final String usuario = "root";
    private final String senha = "";
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    // Estabelece a conexão entre o BD e o Java.
    public void conectar() {
        try { // Tratamento de exceção, tente conectar ao BD.
            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Êxito ao conectar com BD.");
        } catch (SQLException e) { // Caso não seja possível conectar ao BD.
            System.err.println("Falha ao conectar com BD.\n" + e); // Exibe a mensagem e ainda indica o que causou o erro. 
            System.exit(0);
        }
    }

    // Desconecta do BD.
    public void desconectar() {
        try {
            conn.close();
            System.out.println("Êxito ao desconectar do BD.");
        } catch (SQLException e) {
            System.err.println("Falha ao desconectar do BD.\n" + e);
            System.exit(0);
        }
    }

    // Bloco responsável por adicionar um professor ao BD.
    public void adicionarMembro(Professor professor) {
        try {
            String sql = "INSERT INTO professor (nome, membro, semestre, curso_lecionado, salario)"
                    + "VALUES(?, ?, ?, ?, ?)"; // Variável sql do tipo String, será usada no comando abaixo.
            st = conn.prepareStatement(sql); // Através da variável sql contendo o comando em String acima, insere um professor na tabela do DB.
            st.setString(1, professor.getNome());
            st.setString(2, professor.getMembro());
            st.setString(3, professor.getSemestre());
            st.setString(4, professor.getCursoLecionado());
            st.setDouble(5, professor.getSalario());
            st.executeUpdate(); // Executa a instrução SQL citada anterioremente.
            System.out.println("Professor adicionado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Falha ao adicionar professor.\n" + e);
        }
    }

    // Utilizando sobrecarga, bloco responsável por adicionar um aluno ao BD.
    public void adicionarMembro(Estudante estudante) { // Método com o mesmo nome do anterior, porém recebendo parâmetros diferentes.
        try {
            String sql = "INSERT INTO estudante (nome, membro, semestre, curso, media_final)"
                    + "VALUES(?, ?, ?, ?, ?)";
            st = conn.prepareStatement(sql);
            st.setString(1, estudante.getNome());
            st.setString(2, estudante.getMembro());
            st.setString(3, estudante.getSemestre());
            st.setString(4, estudante.getCurso());
            st.setDouble(5, estudante.getMediaFinal());
            st.executeUpdate();
            System.out.println("Aluno adicionado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Falha ao adicionar aluno.\n" + e);
        }
    }

    // Bloco responsável por consultar os dados dos professores através do Id.
    public Object consultarIdProfessor(int id) {
        Professor p = null;
        try {
            String sql = "SELECT * FROM professor WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                p = new Professor();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setMembro(rs.getString("membro"));
                p.setSemestre(rs.getString("semestre"));
                p.setCursoLecionado(rs.getString("curso_lecionado"));
                p.setSalario(rs.getDouble("salario"));
            }
        } catch (SQLException e) {
            System.err.println("Falha ao consultar dados de professores.\n" + e);
        }
        return p;
    }

    // Bloco responsável por consultar os dados dos estudantes através do Id.
    public Object consultarIdEstudante(int id) {
        Estudante a = null;
        try {
            String sql = "SELECT * FROM estudante WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                a = new Estudante();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setMembro(rs.getString("membro"));
                a.setSemestre(rs.getString("semestre"));
                a.setCurso(rs.getString("curso"));
                a.setMediaFinal(rs.getDouble("media_final"));
            }
        } catch (SQLException e) {
            System.err.println("Falha ao consultar dados de alunos.\n" + e);
        }
        return a;
    }

    // Bloco responsável por remover um professor do BD através do Id.
    public void removerProfessor(int id) {
        try {
            if (consultarIdProfessor(id) != null) { // O bloco if recebe o método com o parâmetro Id, se o Id não for um valor nulo, ele remove um professor da tabela.
                String sql = "DELETE FROM professor WHERE id = ?";
                st = conn.prepareStatement(sql);
                st.setInt(1, id);
                st.execute();
                System.out.println("Professor removido com sucesso.");
            } else { // Se o valor do Id for nulo, quer dizer que o professor não existe na tabela.
                System.err.println("Professor não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Falha ao remover professor.\n" + e);
        }
    }

    // Bloco responsável por remover um estudante do BD através do Id.
    public void removerEstudante(int id) {
        try {
            if (consultarIdEstudante(id) != null) { // O bloco if recebe o método com o parâmetro Id, se o Id não for um valor nulo, ele remove um estudante da tabela.
                String sql = "DELETE FROM estudante WHERE id = ?";
                st = conn.prepareStatement(sql);
                st.setInt(1, id);
                st.execute();
                System.out.println("Estudante removido com sucesso.");
            } else { // Se o valor do Id for nulo, quer dizer que o estudante não existe na tabela.
                System.err.println("Estudante não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Falha ao remover estudante.\n" + e);
        }
    }

    // Bloco responsável por atualizar o nome do professor através do Id.
    public void atualizarNomeProfessor(int id, String nome) { // Recebe como parâmetros o Id para identificar o professor, e o nome para alteração.
        try {
            if (consultarIdProfessor(id) != null) {
                String sql = "UPDATE professor SET nome = ? WHERE id = ?";
                st = conn.prepareStatement(sql);
                st.setString(1, nome);
                st.setInt(2, id);
                st.execute();
                System.out.println("Êxito ao atualizar nome do professor.");
            } else {
                System.err.println("Professor não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Falha ao atualizar nome do professor.\n" + e);
        }
    }

    // Os próximos blocos serão responsáveis por atualizar os dados dos professores no BD.
    public void atualizarMembroProfessor(int id, String membro) {
        try {
            if (consultarIdProfessor(id) != null) {
                String sql = "UPDATE professor SET membro = ? WHERE id = ?";

                st = conn.prepareStatement(sql);
                st.setString(1, membro);
                st.setInt(2, id);
                st.execute();
                System.out.println("Êxito ao atualizar cargo do professor.");
            } else {
                System.err.println("Professor não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Falha ao atualizar cargo do professor.\n" + e);
        }
    }

    public void atualizarSemestreProfessor(int id, String semestre) {
        try {
            if (consultarIdProfessor(id) != null) {
                String sql = "UPDATE professor SET semestre = ? WHERE id = ?";

                st = conn.prepareStatement(sql);
                st.setString(1, semestre);
                st.setInt(2, id);
                st.execute();
                System.out.println("Êxito ao atualizar semestre do professor.");
            } else {
                System.err.println("Professor não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Falha ao atualizar semestre do professor.\n" + e);
        }
    }

    public void atualizarCursoLecionadoProfessor(int id, String cursoLecionado) {
        try {
            if (consultarIdProfessor(id) != null) {
                String sql = "UPDATE professor SET curso_lecionado = ? WHERE id = ?";

                st = conn.prepareStatement(sql);
                st.setString(1, cursoLecionado);
                st.setInt(2, id);
                st.execute();
                System.out.println("Êxito ao atualizar curso lecionado pelo professor.");
            } else {
                System.err.println("Professor não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Falha ao atualizar curso lecionado pelo professor.\n" + e);
        }
    }

    public void atualizarSalarioProfessor(int id, double salario) {
        try {
            if (consultarIdProfessor(id) != null) {
                String sql = "UPDATE professor SET salario = ? WHERE id = ?";

                st = conn.prepareStatement(sql);
                st.setDouble(1, salario);
                st.setInt(2, id);
                st.execute();
                System.out.println("Êxito ao atualizar salário do professor.");
            } else {
                System.err.println("Professor não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Falha ao atualizar salário do professor.\n" + e);
        }
    }

    // Os próximos blocos serão responsáveis por atualizar os dados dos alunos no BD.
    public void atualizarNomeEstudante(int id, String nome) {
        try {
            if (consultarIdEstudante(id) != null) {
                String sql = "UPDATE estudante SET nome = ? WHERE id = ?";

                st = conn.prepareStatement(sql);
                st.setString(1, nome);
                st.setInt(2, id);
                st.execute();
                System.out.println("Êxito ao atualizar nome do aluno.");
            } else {
                System.err.println("Aluno não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Falha ao atualizar nome do aluno.\n" + e);
        }
    }

    public void atualizarMembroEstudante(int id, String membro) {
        try {
            if (consultarIdEstudante(id) != null) {
                String sql = "UPDATE estudante SET membro = ? WHERE id = ?";

                st = conn.prepareStatement(sql);
                st.setString(1, membro);
                st.setInt(2, id);
                st.execute();
                System.out.println("Êxito ao atualizar membro do aluno.");
            } else {
                System.err.println("Aluno não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Falha ao atualizar membro do aluno.\n" + e);
        }
    }

    public void atualizarSemestreEstudante(int id, String semestre) {
        try {
            if (consultarIdEstudante(id) != null) {
                String sql = "UPDATE estudante SET semestre = ? WHERE id = ?";

                st = conn.prepareStatement(sql);
                st.setString(1, semestre);
                st.setInt(2, id);
                st.execute();
                System.out.println("Êxito ao atualizar semestre do aluno.");
            } else {
                System.err.println("Aluno não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Falha ao atualizar semestre do aluno.\n" + e);
        }
    }

    public void atualizarCursoEstudante(int id, String curso) {
        try {
            if (consultarIdEstudante(id) != null) {
                String sql = "UPDATE estudante SET curso = ? WHERE id = ?";

                st = conn.prepareStatement(sql);
                st.setString(1, curso);
                st.setInt(2, id);
                st.execute();
                System.out.println("Êxito ao atualizar curso do aluno.");
            } else {
                System.err.println("Aluno não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Falha ao atualizar curso do aluno.\n" + e);
        }
    }

    public void atualizarMediaFinalEstudante(int id, double mediaFinal) {
        try {
            if (consultarIdEstudante(id) != null) {
                String sql = "UPDATE estudante SET media_final = ? WHERE id = ?";

                st = conn.prepareStatement(sql);
                st.setDouble(1, mediaFinal);
                st.setInt(2, id);
                st.execute();
                System.out.println("Êxito ao atualizar média final do aluno.");
            } else {
                System.err.println("Aluno não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Falha ao atualizar média final do aluno.\n" + e);
        }
    }

    // Bloco responsável por listar os professores presentes no BD.
    public List<Professor> listarProfessor() { // Aqui foi usada a interface ''List'' e a classe ''ArrayList'' para implementar esta interface.
        List<Professor> lista = new ArrayList<>(); // Aqui foi declarada e instanciada uma lista do tipo ''Professor''.
        String sql = "SELECT * FROM professor";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            Professor p;
            while (rs.next()) {
                p = new Professor();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setMembro(rs.getString("membro"));
                p.setSemestre(rs.getString("semestre"));
                p.setCursoLecionado(rs.getString("curso_lecionado"));
                p.setSalario(rs.getDouble("salario"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Falha ao listar professores.\n" + e);
        }
        return lista;
    }

    // Bloco responsável por listar os alunos presentes no BD.
    public List<Estudante> listarAlunos() {
        List<Estudante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudante";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            Estudante a;
            while (rs.next()) {
                a = new Estudante();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setMembro(rs.getString("membro"));
                a.setSemestre(rs.getString("semestre"));
                a.setCurso(rs.getString("curso"));
                a.setMediaFinal(rs.getDouble("media_final"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Falha ao listar alunos.\n" + e);
        }
        return lista;
    }

}
