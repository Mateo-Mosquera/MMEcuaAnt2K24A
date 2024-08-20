package MMDataAccess.MMDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MMDataAccess.MMIDAO;
import MMDataAccess.MMDTO.MMSexoDTO;
import MMDataAccess.MMDataHelper.MMDataHelper;

public class MMSexoDAO implements MMIDAO<MMSexoDTO> {
    private Connection mmConnection;

    public MMSexoDAO() {
        this.mmConnection = MMDataHelper.mmConection();
    }

    private void ensureConnectionOpen() throws SQLException {
        if (mmConnection == null || mmConnection.isClosed()) {
            mmConnection = MMDataHelper.mmConection();
        }
    }

    @Override
    public boolean mmCreate(MMSexoDTO entity) throws Exception {
        ensureConnectionOpen();
        String sql = "INSERT INTO CatalogoAlimento (idCatalogoTipoAl, nombre, descripcion) VALUES (3, ?, ?)";
        try (PreparedStatement preparedStatement = mmConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear Sexo", e);
        }
    }

    @Override
    public List<MMSexoDTO> mmReadAll() throws Exception {
        ensureConnectionOpen();
        List<MMSexoDTO> sexos = new ArrayList<>();
        String sql = "SELECT idCatalogoAl, nombre, descripcion, estado, FechaCreacion FROM CatalogoAlimento WHERE estado = 'A' AND idCatalogoTipoAl = 3";
        try (PreparedStatement preparedStatement = mmConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                MMSexoDTO sexo = new MMSexoDTO();
                sexo.setIdCatalogoAl(rs.getInt("idCatalogoAl"));
                sexo.setNombre(rs.getString("nombre"));
                sexo.setDescripcion(rs.getString("descripcion"));
                sexo.setEstado(rs.getString("estado"));
                sexo.setFechaCreacion(rs.getString("FechaCreacion"));
                sexos.add(sexo);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Sexos", e);
        }
        return sexos;
    }

    @Override
    public boolean mmUpdate(MMSexoDTO entity) throws Exception {
        ensureConnectionOpen();
        String sql = "UPDATE CatalogoAlimento SET nombre = ?, descripcion = ? WHERE idCatalogoAl = ?";
        try (PreparedStatement preparedStatement = mmConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());
            preparedStatement.setInt(3, entity.getIdCatalogoAl());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar Sexo", e);
        }
    }

    @Override
    public boolean mmDelete(int id) throws Exception {
        ensureConnectionOpen();
        String sql = "UPDATE CatalogoAlimento SET estado = 'X' WHERE idCatalogoAl = ?";
        try (PreparedStatement preparedStatement = mmConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar Sexo", e);
        }
    }

    @Override
    public MMSexoDTO mmReadBy(Integer id) throws Exception {
        ensureConnectionOpen();
        MMSexoDTO sexo = null;
        String sql = "SELECT idCatalogoAl, nombre, descripcion, estado, FechaCreacion FROM CatalogoAlimento WHERE idCatalogoAl = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = mmConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    sexo = new MMSexoDTO();
                    sexo.setIdCatalogoAl(rs.getInt("idCatalogoAl"));
                    sexo.setNombre(rs.getString("nombre"));
                    sexo.setDescripcion(rs.getString("descripcion"));
                    sexo.setEstado(rs.getString("estado"));
                    sexo.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Sexo por ID", e);
        }
        return sexo;
    }
}
