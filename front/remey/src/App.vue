<template>
	<div id="app">
		<FormPostShopping/>
		<br />
		<SelectYearMonth/>
		<br />
		<ShoppingList/>
	</div>
</template>

<script>

import ShoppingList from './components/ShoppingList.vue'
import FormPostShopping from './components/FormPostShopping.vue'
import SelectYearMonth from './components/SelectYearMonth.vue'

export default {
	components: {
		ShoppingList,
		FormPostShopping,
		SelectYearMonth
	}
}
</script>
