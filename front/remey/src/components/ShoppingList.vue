<template>
	<table class="center">
		<tr v-for="data in shoppingData" :key="data.shopping_id">
			<td>{{data.shopping_id}}</td><td>{{data.date}}</td><td>{{data.amount}}</td>
		</tr>
	</table>
</template>

<script>
import {mapState} from 'vuex'

export default{
	computed: mapState({
		shoppingData: state => state.shopping.shoppingData
	}),
	created(){
		//this.$store.dispatch('shopping/getMonthShopping');
	}
}
</script>
