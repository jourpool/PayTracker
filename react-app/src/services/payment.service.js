import http from "../http-common";

const default_size = 10;

class PaymentDataService {
  getAll(page = 0, size = default_size) {
    return http.get(`/payment?page=${page}&size=${size}`);
  }

  findByFilter(customerId, typeName, amount = null, page = 0, size = default_size) {
    console.log(typeName);
    let url = `/payment/filter?customerId=${customerId}&typeName=${typeName}&page=${page}&size=${size}`;
  
    if (amount !== null) {
      url += `&amount=${amount}`;
    }
  
    return http.get(url);
  }
}

export default new PaymentDataService();
