<template>
	<div class="center">
		<div class="post-shopping-data-table">
			<table class="center-object">
				<tr>
					<th class="post-shopping-data">出費日付:</th>
					<td class="post-shopping-data"><input v-model="shoppingDate" placeholder="yyyy/MM/dd"></td>
				</tr>
				<tr>
					<th class="post-shopping-data">金額:</th>
					<td class="post-shopping-data"><input v-model.number="amount" placeholder="">円</td>
				</tr>
			</table>
		</div>
		<button class="button" @click="postShoppingAmount({'shoppingDate': shoppingDate, 'amount': amount})">
			記録
		</button>
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