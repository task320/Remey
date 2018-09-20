<template>
  <div id="app">
	<input v-model="shoppingDate" placeholder="Shopping Date">
	\<input v-model.number="amount" placeholder="Amount">
	<button @click="postShoppingAmount({'shoppingDate': shoppingDate, 'amount': amount})">POST</button><br />
	<br />
	<button @click="getMonthShopping()">Get</button><br /><br />
	RESULT:{{ resultApi }}<br />
	JSON:{{responseJson}}<br />
	<br />
	<table class="center">
		<tr v-for="data in shoppingData" :key="data.shopping_id">
			<td>{{data.shopping_id}}</td><td>{{data.date}}</td><td>{{data.amount}}</td>
		</tr>
	</table>
  </div>
</template>

<script>
import {mapState, mapActions} from 'vuex'

export default {
  data(){
		return{
			shoppingDate: '',
			amount: '0',
			resultApi: 'Defult',
			responseJson:'Nothing'
		}
	},
  computed: mapState({
	shoppingData: state => state.shopping.shoppingData
  }),
  methods: mapActions('shopping',[
	'postShoppingAmount',
	'getMonthShopping'
  ])
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
