package pl.javastart.readstack.domain.category;

import pl.javastart.readstack.config.DataSourceProvider;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDao {

    private final DataSource dataSource;

    public CategoryDao() {
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> findAll() {
        final String query = """
                SELECT
                    id, name, description
                FROM
                    category
                """;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Category> allCategories = new ArrayList<>();
            while (resultSet.next()) {
                Category category = mapRow(resultSet);
                allCategories.add(category);
            }
            return allCategories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Category mapRow(ResultSet set) throws SQLException {
        int id = set.getInt("id");
        String name = set.getString("name");
        String description = set.getString("description");
        return new Category(id, name, description);
    }

    public Optional<Category> findById(int categoryId) {
        final String query = """
                SELECT
                    id, name, description
                FROM
                    category
                WHERE
                    id = ?
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Category category = mapRow(resultSet);
                return Optional.of(category);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}