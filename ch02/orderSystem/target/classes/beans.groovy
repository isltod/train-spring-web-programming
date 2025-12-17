beans {
    customerRepository(com.mycompany.ordersystem.CustomerRepositoryImpl) {}
    customerService(com.mycompany.ordersystem.CustomerServiceImpl) {
//        이 경우도 setter로 넣는거라네...
        customerRepository = customerRepository
    }
}
