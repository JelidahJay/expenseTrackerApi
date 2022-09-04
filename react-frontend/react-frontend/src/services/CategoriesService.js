import axios from 'axios';

const API_BASE_URL = "http://localhost:8080/api/v1/expenseTracker/";

class CategoriesService{
        getCategories(){
            return axios.get(API_BASE_URL + 'categories');
        }

        getExpenses(){
            return axios.get(API_BASE_URL + 'expenses');
        }

        createCategory(Category){
            return axios.post(API_BASE_URL + 'category/create', Category);
        }
    
        getCategoryById(CategoryID){
            return axios.get(API_BASE_URL + 'categories/' + CategoryID);
        }
    
        updateCategory(CategoryID){
            return axios.put(API_BASE_URL + 'categoryUpdate/' + CategoryID);
        }
    
        deleteCategory(CategoryID){
            return axios.delete(API_BASE_URL + 'categoryDelete/' + CategoryID);
        }

        createExpense(Expense){
            return axios.post(API_BASE_URL + 'expense/create', Expense);
        }
    
        getExpenseById(ExpenseID){
            return axios.get(API_BASE_URL + 'expenses/' + ExpenseID);
        }
    
        updateExpense(ExpenseID){
            return axios.put(API_BASE_URL + 'expenseUpdate/' + ExpenseID);
        }
    
        deleteExpense(ExpenseID){
            return axios.delete(API_BASE_URL + 'expenseDelete/' + ExpenseID);
        }
}

export default new CategoriesService()