<template>
	<div>
		Shopping Year/Month<br />
		<select v-model="shoppingYearMonth">
			<option  v-for="(data, index) in yearMonth" :key="index">{{ data }}</option>
		</select>
	</div>
</template>

<script>
import {mapState} from 'vuex'

export default{
	computed: mapState({
		yearMonth: state => state.shopping.yearMonth
	}),
	created(){
		this.$store.dispatch('shopping/getPullDownYearMonthValues');
	}
}
</script>