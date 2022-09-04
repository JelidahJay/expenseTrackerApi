import React,{Component} from "react"; 
import {Modal,Button,Row,Col,Form} from 'react-bootstrap';
import Snackbar from '@mui/material/Snackbar';
import IconButton from '@mui/material/IconButton';

export class AddExpense extends Component{
  constructor(props){
    super(props);
    this.state = {snackbaropen:false,snackbarmsg:''};
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  snackbarClose = (event) => {
    this.setState({snackbaropen:false});
  }
handleSubmit(event){
      event.preventDefault();
      fetch('http://localhost:8080/api/v1/expenseTracker/expense/create', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body:JSON.stringify({
          expenseID:event.target.expenseID.value,
          expenseDate:event.target.expenseDate.value,
          Amount:event.target.Amount.value,
          categoryID:event.target.categoryID.value,
        })
      })
      .then(res => res.json())
      .then((result) => {
        this.setState({snackbaropen:true,snackbarmsg:'Successfully added a new Expense.'});
      },
      (error) => {
        this.setState({snackbaropen:false,snackbarmsg:'Failed to add Expense.'});
      })
    }
    render(){
        return(
            <div className="container">
              <Snackbar anchorOrigin={{vertical:'bottom', horizontal:'left'}}
              open = {this.state.snackbaropen}
              autoHideDuration = {3000}
              onClose = {this.snackbarClose}
              message = {<span id="message-id">{this.state.snackbarmsg}</span>}
              action = {[<IconButton
              key ="close"
              aria-label="Close"
              color="inherit"
              onClick = {this.snackbarClose}>x</IconButton>]}
              ></Snackbar>
            <Modal
            {...this.props}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
             >
              <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                  Add Expense
                </Modal.Title>
              </Modal.Header>
              <Modal.Body>
                <Row>
                  <Col lg>
                  <Form onSubmit = {this.handleSubmit}>
                      <Form.Group controlId="expenseID" className = "mt-2">
                        <Form.Label>Expense ID:</Form.Label>
                        <Form.Control
                        type = "text"
                        name = "expenseID"
                        required
                        placeholder = 'Expense Id' />
                      </Form.Group>

                      <Form.Group controlId="expenseDate" className = "mt-2">
                        <Form.Label>Expense Date:</Form.Label>
                        <Form.Control
                        type = "Date"
                        name = "ExpenseDate"
                        required
                        placeholder = 'Date' />
                      </Form.Group>

                      <Form.Group controlId="Amount" className = "mt-2">
                        <Form.Label>Amount</Form.Label>
                        <Form.Control
                        type = "text"
                        name = "amount"
                        required
                        placeholder = 'Amount Spent' />
                      </Form.Group>

                      <Form.Group controlId="categoryID" className = "mt-2">
                        <Form.Label>Category ID</Form.Label>
                        <Form.Control
                        type = "text"
                        name = "categoryID"
                        required
                        placeholder = 'Category Id' />
                      </Form.Group>

                      <Form.Group>
                        <Button className = "mt-2" variant = 'success' type='submit'>Save</Button>
                        <Button className = "mt-2" variant = 'danger'onClick={this.props.onHide}>Cancel</Button>
                      </Form.Group>
                  </Form>
                  </Col>
                </Row>
                  
              </Modal.Body> 
              <Modal.Footer>
                
              </Modal.Footer>
          </Modal>
          </div>
        )
    }
}

export default AddExpense;