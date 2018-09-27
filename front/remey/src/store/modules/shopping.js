import axios from 'axios';

const state = {
	shoppingData: [],
	yearMonth: []
}

const mutations = {
	setShoppingData(state, data){
		state.shoppingData.unshift({"shopping_id":data.shopping_id, "date":data.date, "amount":data.amount});
	},
	setShoppingDataList(state, data){
		state.shoppingData = data
	},
	setPullDownYearMonthValues(state, data){
		state.yearMonth = data
	}
}

const actions = {
	postShoppingAmount({commit}, {shoppingDate, amount}){   		
		axios.post('http://localhost:8081/remey/post/task_mon',{
			date: shoppingDate,
			amount: amount
		})
		.then(response => {
			if(response.data.status == 'SUCCESS'){
				commit('setShoppingData',response.data.values); 
			}
		})
		.catch(e => {
			alert(e);
		})
	},
	getMonthShopping: function({commit}){
		axios.get('http://localhost:8081/remey/get/month/2018/08/task_mon')
		.then(response => {
			commit('setShoppingDataList',response.data.values);
		})
		.catch(e => {
			alert(e);
		})
	},
	getPullDownYearMonthValues({commit}){   		
		axios.get('http://localhost:8081/remey/get/pull-down-year-month-values/task_mon')
		.then(response => {
			commit('setPullDownYearMonthValues',response.data.values); 
		})
		.catch(e => {
			alert(e);
		})
	},
}

export default {
	namespaced: true,
	state,
	mutations,
	actions
}