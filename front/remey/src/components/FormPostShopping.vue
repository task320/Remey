<template>
	<div>
		Shopping Date:<input v-model="shoppingDate" placeholder="Shopping Date">
		/Amount:<input v-model.number="amount" placeholder="Amount">Yen<br /><br />
		<button @click="postShoppingAmount({'shoppingDate': shoppingDate, 'amount': amount})">POST</button>
	</div>
</template>

<script>
import {mapActions} from 'vuex'

export default{
	data(){
		return{
			shoppingDate: '',
			amount: ''
		}
	},
	methods: mapActions('shopping',[
		'postShoppingAmount'
	])
} 
</script>