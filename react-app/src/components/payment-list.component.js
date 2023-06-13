import React, { Component } from "react";
import PaymentDataService from "../services/payment.service";

export default class PaymentList extends Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
    this.loadMore = this.loadMore.bind(this);
    this.retrievePayments = this.retrievePayments.bind(this);
    this.setActivePayment = this.setActivePayment.bind(this);
    this.search = this.search.bind(this);

    this.state = {
      payments: [],
      currentPayment: null,
      currentIndex: -1,
      showModal: false,

      customerId: "",
      typeName: "",
      amount: "",
      page: 0
    };
  }

  componentDidMount() {
    this.retrievePayments();
  }

  handleChange = (e) => {
    const { id, value } = e.target;
  
    this.setState({
      [id]: value
    }, () => {
      this.search();
    });
  }  

  retrievePayments() {
    const page = this.state.page;
    PaymentDataService.getAll(page)
      .then(response => {
        this.setState(prevState => ({
          payments: [...prevState.payments, ...response.data.payments]
        }));
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  setActivePayment(payment, index) {
    this.setState({
      currentPayment: payment,
      currentIndex: index,
      showModal: true
    });
  }

  search() {
    const { customerId, typeName, amount, page } = this.state;
  
    if (!customerId && !typeName && !amount) {
      return this.retrievePayments();
    }

    if (!customerId || !typeName) {
      return this.setState({ payments: [] });
    }
  
    const formattedAmount = amount && amount.trim() !== "" ? amount : null;
  
    PaymentDataService.findByFilter(customerId, typeName, formattedAmount, page)
      .then(response => {
        // If not found, clear the list and return to page 0
        if (response.status === 204) {
          this.setState({ payments: [], page: 0 });
        }else {
          // Append list
          this.setState(prevState => ({
            payments: [...prevState.payments, ...response.data.payments]
          }));
          console.log(response.data);
        }
      })
      .catch(e => {
        this.setState({ payments: [], page: 0 });
        console.log(e);
      });
  }

  closeModal = () => {
    this.setState({
      showModal: false
    });
  };

  // Lazy loading
  loadMore() {
    this.setState(prevState => ({
      page: prevState.page + 1
    }), () => {
      this.search();
    });
  }

  render() {
    const { customerId, typeName, amount, payments, currentPayment } = this.state;
      return (
        <div>
          <form>
            <div className="row">
              <div className="col">
                <div className="form-group">
                  <label htmlFor="customerId">Customer ID</label>
                  <input type="text" className="form-control" id="customerId" placeholder="Enter Customer ID..." value={customerId} onChange={this.handleChange}/>
                </div>
              </div>
              <div className="col">
                <div className="form-group">
                  <label htmlFor="typeName">Payment Type Name</label>
                  <input type="text" className="form-control" id="typeName" placeholder="Enter Payment type..."  value={typeName} onChange={this.handleChange}/>
                </div>
              </div>
              <div className="col">
                <div className="form-group">
                  <label htmlFor="amount">Amount</label>
                  <input type="text" className="form-control" id="amount" placeholder="Enter Payment amount..."  value={amount} onChange={this.handleChange} />
                </div>
              </div>
            </div>
            {/* <div className="row">
              <div className="col">
                <button type="button" className="btn btn-primary" onClick={this.search}>Search</button>
              </div>
            </div> */}
          </form>     
             
          <table className="table table-bordered table-hover">
            <thead>
              <tr>
                <th>ID</th>
                <th>Customer ID</th>
                <th>Amount</th>
              </tr>
            </thead>
            <tbody>
              {payments.length === 0 ? (
                <tr>
                  <td colSpan="3" className="not-found">Payment Not Found</td>
                </tr>
              ) : (
                payments.map((payment, index) => (
                  <tr onClick={() => this.setActivePayment(payment, index)} key={index}>
                    <td>{payment.id}</td>
                    <td>{payment.customerId}</td>
                    <td>{payment.amount}</td>
                  </tr>
                ))
              )}
            </tbody>
          </table>

          {payments.length > 0 && (
            <div className="row">
              <div className="col">
                <button type="button" className="btn btn-primary" onClick={this.loadMore}>Load More</button>
              </div>
            </div>
          )}

        
          {this.state.showModal && (
            <div className="modal" id="detailsModal" tabIndex="-1" role="dialog" aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                  <div className="modal-header">
                    <h5 className="modal-title" id="detailsModalLabel">Details</h5>
                  </div>
                  <div className="modal-body">
                    <p>ID: {currentPayment && currentPayment.id}</p>
                    <p>Customer ID: {currentPayment && currentPayment.customerId}</p>
                    <p>Amount: {currentPayment && currentPayment.amount}</p>
                    <p>Date: {currentPayment && currentPayment.date}</p>
                  </div>
                  <div className="modal-footer">
                    <button type="button" className="btn btn-primary" onClick={this.closeModal}>Close</button>
                  </div>
                </div>
              </div>
            </div>
          )}
        </div>
    );
  }
}
