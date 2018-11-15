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
		axios.post('/remey/api/post/shopping_data',{
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
	getMonthShopping: function({commit},value){
		var [year, month] = value.split("/")
		axios.get('/remey/api/get/month/' + year + '/' + month)
		.then(response => {
			commit('setShoppingDataList',response.data.values);
		})
		.catch(e => {
			alert(e);
		})
	},
	getPullDownYearMonthValues({commit}){   		
		axios.get('/remey/api/get/pull-down-year-month-values')
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