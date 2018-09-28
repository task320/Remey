<template>
	<div>
		Shopping Year/Month<br />
		<select v-model="shoppingYearMonth" @change="getShoppingList">
			<option  v-for="(data, index) in yearMonth" :key="index" :value="data">{{ data }}</option>
		</select>
	</div>
</template>

<script>
import {mapState} from 'vuex'

export default{
	data(){
		return{
			shoppingYearMonth: ''
		}
	},
	methods:{
		getShoppingList(event){
			this.$store.dispatch('shopping/getMonthShopping', event.target.value)
		}
	},
	computed: mapState({
		yearMonth: state => state.shopping.yearMonth
	}),
	created(){
		this.$store.dispatch('shopping/getPullDownYearMonthValues')
	},
	mounted: function(){
		let today = new Date();
		let dd = today.getDate();

		let mm = today.getMonth()+1; 
		const yyyy = today.getFullYear();
		if(dd<10) 
		{
			dd=`0${dd}`;
		} 

		if(mm<10) 
		{
			mm=`0${mm}`;
		} 
		this.shoppingYearMonth = `${yyyy}/${mm}`;
		this.$store.dispatch('shopping/getMonthShopping', this.shoppingYearMonth)
	}
}
</script>