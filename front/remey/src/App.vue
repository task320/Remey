<template>
  <div id="app">
	<input v-model="shopping_date" placeholder="Shopping Date">
	\<input v-model.number="amount" placeholder="Amount">
	<button v-on:click="postShoppingAmount">POST</button><br />
	<br />
	<button v-on:click="getTest">Get</button><br /><br />
	RESULT:{{ result }}<br />
	JSON:{{response_json}}<br />
	<br />
	<table class="center">
		<tr v-for="data in shopping_data" :key="data.shopping_id">
			<td>{{data.shopping_id}}</td><td>{{data.date}}</td><td>{{data.amount}}</td>
		</tr>
	</table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'app',
  data(){
	return{
		result: 'Defult',
		shopping_data: [],
		response_json:'Nothing'
	}
  },
  methods: {
	postShoppingAmount: function(){
		alert('TEST')      
		axios.post('http://localhost:8081/remey/post/task_mon',{
			date: this.shopping_date,
			amount: this.amount
		})
		.then(response => {
			alert('response');
			this.result = response.data.status;
			this.response_json = response.data
			if(this.result == 'SUCCESS'){
				var res_value = response.data.values; 
				this.shopping_data.unshift({"shopping_id":res_value.shopping_id, "date":res_value.date, "amount":res_value.amount});
			}
		})
		.catch(e => {
			alert(e);
			this.result = 'Error';
		})
	},
	getTest: function(){
		axios.get('http://localhost:8081/remey/get/month/2018/08/task_mon')
		.then(response => {
			alert(response.data.status);
			this.result = response.data.status;
			this.shopping_data = response.data.values
		})
		.catch(e => {
			alert(e);
		})
	}
  }
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
