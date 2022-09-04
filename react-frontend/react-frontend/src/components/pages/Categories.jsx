import React, { Component } from 'react';
import CategoriesService from '../../services/CategoriesService';
import {AddCategory} from '../modals/categories/AddCategory';
import {EditCategory} from '../modals/categories/EditCategory';

export class Categories extends Component {
    constructor(props){
        super(props)

        this.state = {
            categories: [],
            addModalShow : false,
            editModalShow : false
        }
        this.deleteCategory = this.deleteCategory.bind(this);
    }
    deleteCategory(categoryID){
        if(window.confirm('Are you sure?')){
        CategoriesService.deleteCategory(categoryID).then( res => {
            this.setState({categories: this.state.categories.filter(category => category.categoryID !== categoryID)});
        });
     }
    }
    componentDidMount(){
        CategoriesService.getCategories().then((res) =>{
            this.setState({categories: res.data});
        });
    }
    componentDidUpdate(){
        this.componentDidMount();
    }

render() {
        const {categoryid, categoryname} = this.state;
        let addModalClose = () => this.setState({addModalShow:false});
        let editModalClose = () => this.setState({editModalShow:false});
        return (
            <div className='mt-5'>
                <div className="table-title">
				<div className="row">
					<div className="col-sm-6">
						<h2>Manage <b>Categories</b></h2>
					</div>
                    <div className="col-sm-6">
						<a href="#addCategory" className="btn btn-success" data-toggle="modal" onClick= {() => this.setState({addModalShow:true})}>
                            <i className="material-icons">&#xE147;</i> <span>Add New Category</span></a>
                            <AddCategory 
                            show = {this.state.addModalShow}
                            onHide = {addModalClose}
                            />					
					</div>
				</div>
                </div>
                <table className="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Category ID</th>
                            <th>Category Name</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>{this.state.categories.map(
                                                category =>
                                                <tr key = {category.categoryID}>
                                                    <td>
                                                        {category.categoryID}
                                                    </td>
                                                    <td>
                                                        {category.categoryName}
                                                    </td>
                                                    <td>      
                                                        <EditCategory
                                                        show = {this.state.editModalShow}
                                                        onHide = {editModalClose}
                                                        categoryid = {categoryid}
                                                        categoryname = {this.state.categoryname}
                                                          />
                                                        <a href="#EditCategory" className="edit" data-toggle="modal" onClick = { () => this.setState({editModalShow:true,
                                                            categoryid:category.categoryID,categoryname:category.categoryName})}>
                                                            <i className="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>

                                                                                                    
                                                        <a href="#DeleteCategory" className="delete" data-toggle="modal" 
                                                        onClick={ () => this.deleteCategory(category.categoryID)}>
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

export default Categories;