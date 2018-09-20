import axios from 'axios';

const state = {
	shoppingData: []
}

const mutations = {
	setShoppingData(state, data){
		state.shoppingData.unshift({"shopping_id":data.shopping_id, "date":data.date, "amount":data.amount});
	},
	setShoppingDataList(state, data){
		state.shoppingData = data
	}
}

const actions = {
	postShoppingAmount({commit, rootState}, {shoppingDate, amount}){   		
		axios.post('http://localhost:8081/remey/post/task_mon',{
			date: shoppingDate,
			amount: amount
		})
		.then(response => {
			rootState.result = response.data.status;
			rootState.responseJson = response.data
			if(rootState.result == 'SUCCESS'){
				commit('setShoppingData',response.data.values); 
			}
		})
		.catch(e => {
			alert(e);
			rootState.result = 'Error';
		})
	},
	getMonthShopping: function({commit, rootState}){
		axios.get('http://localhost:8081/remey/get/month/2018/08/task_mon')
		.then(response => {
			rootState.result = response.data.status;
			commit('setShoppingDataList',response.data.values);
		})
		.catch(e => {
			alert(e);
		})
	}
}

export default {
	namespaced: true,
	state,
	mutations,
	actions
}