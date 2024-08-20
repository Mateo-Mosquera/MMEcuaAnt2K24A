package MMDataAccess.MMDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MMDataAccess.MMIDAO;
import MMDataAccess.MMDTO.MMProvinciaDTO;
import MMDataAccess.MMDataHelper.MMDataHelper;

public class MMProvinciaDAO implements MMIDAO<MMProvinciaDTO> {
    private Connection mmConnection;

    public MMProvinciaDAO() {
        this.mmConnection = MMDataHelper.mmConection();
    }

    private void ensureConnectionOpen() throws SQLException {
        if (mmConnection == null || mmConnection.isClosed()) {
            mmConnection = MMDataHelper.mmConection();
        }
    }

    @Override
    public boolean mmCreate(MMProvinciaDTO entity) throws Exception {
        ensureConnectionOpen();
        String sql = "INSERT INTO CatalogoGeografia (idCatalogoTipoGeo, idRegion, nombre, descripcion) VALUES (3, ?, ?, ?)";
        try (PreparedStatement preparedStatement = mmConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdRegion());
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setString(3, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear Provincia", e);
        }
    }

    @Override
    public List<MMProvinciaDTO> mmReadAll() throws Exception {
        ensureConnectionOpen();
        List<MMProvinciaDTO> provincias = new ArrayList<>();
        String sql = "SELECT idCatalogoGeo, idRegion, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE estado = 'A' AND idCatalogoTipoGeo = 3";
        try (PreparedStatement preparedStatement = mmConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                MMProvinciaDTO provincia = new MMProvinciaDTO();
                provincia.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                provincia.setIdRegion(rs.getInt("idRegion"));
                provincia.setNombre(rs.getString("nombre"));
                provincia.setDescripcion(rs.getString("descripcion"));
                provincia.setEstado(rs.getString("estado"));
                provincia.setFechaCreacion(rs.getString("FechaCreacion"));
                provincias.add(provincia);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Provincias", e);
        }
        return provincias;
    }

    @Override
    public boolean mmUpdate(MMProvinciaDTO entity) throws Exception {
        ensureConnectionOpen();
        String sql = "UPDATE CatalogoGeografia SET idRegion = ?, nombre = ?, descripcion = ? WHERE idCatalogoGeo = ?";
        try (PreparedStatement preparedStatement = mmConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdRegion());
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setString(3, entity.getDescripcion());
            preparedStatement.setInt(4, entity.getIdCatalogoGeo());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar Provincia", e);
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
            throw new Exception("Error al eliminar Provincia", e);
        }
    }

    @Override
    public MMProvinciaDTO mmReadBy(Integer id) throws Exception {
        ensureConnectionOpen();
        MMProvinciaDTO provincia = null;
        String sql = "SELECT idCatalogoGeo, idRegion, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE idCatalogoGeo = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = mmConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    provincia = new MMProvinciaDTO();
                    provincia.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                    provincia.setIdRegion(rs.getInt("idRegion"));
                    provincia.setNombre(rs.getString("nombre"));
                    provincia.setDescripcion(rs.getString("descripcion"));
                    provincia.setEstado(rs.getString("estado"));
                    provincia.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Provincia por ID", e);
        }
        return provincia;
    }
}
