package DependencyInjection;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer findCustomerById(int id) {
        // Mock implementation for demonstration purposes
        return new Customer(id, "John Doe");
    }
}
