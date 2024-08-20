package MMDataAccess.MMDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MMDataAccess.MMIDAO;
import MMDataAccess.MMDTO.MMPaisDTO;
import MMDataAccess.MMDataHelper.MMDataHelper;

public class MMPaisDAO implements MMIDAO<MMPaisDTO> {
    private Connection mmConnection;

    public MMPaisDAO() {
        this.mmConnection = MMDataHelper.mmConection();
    }

    private void ensureConnectionOpen() throws SQLException {
        if (mmConnection == null || mmConnection.isClosed()) {
            mmConnection = MMDataHelper.mmConection();
        }
    }

    @Override
    public boolean mmCreate(MMPaisDTO entity) throws Exception {
        ensureConnectionOpen();
        String sql = "INSERT INTO CatalogoGeografia (idCatalogoTipoGeo, nombre, descripcion) VALUES (1, ?, ?)";
        try (PreparedStatement preparedStatement = mmConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear País", e);
        }
    }

    @Override
    public List<MMPaisDTO> mmReadAll() throws Exception {
        ensureConnectionOpen();
        List<MMPaisDTO> paises = new ArrayList<>();
        String sql = "SELECT idCatalogoGeo, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE estado = 'A' AND idCatalogoTipoGeo = 1";
        try (PreparedStatement preparedStatement = mmConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                MMPaisDTO pais = new MMPaisDTO();
                pais.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                pais.setNombre(rs.getString("nombre"));
                pais.setDescripcion(rs.getString("descripcion"));
                pais.setEstado(rs.getString("estado"));
                pais.setFechaCreacion(rs.getString("FechaCreacion"));
                paises.add(pais);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Países", e);
        }
        return paises;
    }

    @Override
    public boolean mmUpdate(MMPaisDTO entity) throws Exception {
        ensureConnectionOpen();
        String sql = "UPDATE CatalogoGeografia SET nombre = ?, descripcion = ? WHERE idCatalogoGeo = ?";
        try (PreparedStatement preparedStatement = mmConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());
            preparedStatement.setInt(3, entity.getIdCatalogoGeo());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar País", e);
        }
    }

    @Override
    public boolean mmDelete(int id) throws Exception {
        ensureConnectionOpen();
        String sql = "UPDATE CatalogoGeografia SET estado = 'X' WHERE idCatalogoGeo = ?";
        try (PreparedStatement preparedStatement = mmConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar País", e);
        }
    }

    @Override
    public MMPaisDTO mmReadBy(Integer id) throws Exception {
        ensureConnectionOpen();
        MMPaisDTO pais = null;
        String sql = "SELECT idCatalogoGeo, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE idCatalogoGeo = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = mmConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    pais = new MMPaisDTO();
                    pais.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                    pais.setNombre(rs.getString("nombre"));
                    pais.setDescripcion(rs.getString("descripcion"));
                    pais.setEstado(rs.getString("estado"));
                    pais.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer País por ID", e);
        }
        return pais;
    }
}
