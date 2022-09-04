import React, { Component } from 'react';
import CategoriesService from '../../services/CategoriesService';
import {AddExpense} from '../modals/expenses/AddExpense';
import {EditExpense} from '../modals/expenses/EditExpense';

export class expenses extends Component {
    constructor(props){
        super(props)

        this.state = {
            expenses: [],
            addModalShow : false,
            editModalShow : false
        }
    }
    componentDidMount(){
        CategoriesService.getExpenses().then((res) =>{
            this.setState({expenses: res.data});
        });
    }
    componentDidUpdate(){
        this.componentDidMount();
    }
    deleteExpense(expenseID){
        if(window.confirm('Are you sure?')){
        CategoriesService.deleteExpense(expenseID).then( res => {
            this.setState({expenses: this.state.expenses.filter(expense => expense.expenseID !== expenseID)});
        });
     }
    }
render() {
        const {expenseid, expensedate, amount, categoryid} = this.state;
        let addModalClose = () => this.setState({addModalShow:false});
        let editModalClose = () => this.setState({editModalShow:false});
        return (
            <div className='mt-5'>
                <div className="table-title">
				<div className="row">
					<div className="col-sm-6">
						<h2>Manage <b>Expenses</b></h2>
					</div>
                    <div className="col-sm-6">
						<a href="#addExpense" className="btn btn-success" data-toggle="modal" onClick= {() => this.setState({addModalShow:true})}>
                            <i className="material-icons">&#xE147;</i> <span>Add New Expense</span></a>
                            <AddExpense
                            show = {this.state.addModalShow}
                            onHide = {addModalClose}
                            />					
					</div>
				</div>
			</div>
			<table className="table table-striped table-hover">
				<thead>
					<tr>
						<th>Expense ID</th>
						<th>Date</th>
						<th>Amount (ZMK)</th>
						<th>Category ID</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>{this.state.expenses.map(
                                            expense =>
                                            <tr key = {expense.expenseid}>
                                                <td>
                                                    {expense.expenseID}
                                                </td>
                                                <td>
                                                    {expense.expenseDate}
                                                </td>
                                                <td>{expense.amount}</td>
                                                <td>{expense.categoryID}</td>
                                                <td>
                                                <EditExpense
                                                show = {this.state.editModalShow}
                                                onHide = {editModalClose}
                                                expenseid= {this.state.expenseid}
                                                expensedate = {this.state.expensedate}
                                                amount = {this.state.amount}
                                                categoryid = {this.state.categoryid}
                                                                                    /> 
                                                <a href="#EditExpense" className="edit" data-toggle="modal" onClick = { () => this.setState({editModalShow:true, 
                                                expenseid:expense.expenseid, expensedate:expense.expensedate, amount:expense.amount, categoryid:expense.categoryid})}><i className="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                                 <a href="#DeleteExpense" className="delete" data-toggle="modal" 
                                                        onClick={ () => this.deleteExpense(expense.expenseID)}>
                                                            <i className="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                                </td>
                                            </tr>
                                        )
                        }                   
                </tbody>
            </table>
               
 </div>
        );
    }
}

export default expenses;