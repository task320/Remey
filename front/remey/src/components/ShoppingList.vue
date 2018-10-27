<template>
	<table class="center-object shopping-list">
		<th class="common-style-shopping-list shopping-list-date">日付</th>
		<th class="common-style-shopping-list shopping-list-amount">金額</th>
		<tr class="common-style-shopping-list" v-for="data in shoppingData" :key="data.shopping_id">
			<td class="common-style-shopping-list shopping-list-date">
				{{data.date}}
				<div :id=data.shopping_id />
			</td>
			<td class="common-style-shopping-list shopping-list-amount">
				{{data.amount}} 円
			</td>
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
