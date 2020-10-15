<template>
  <v-container>
    <v-form ref="form">
      <v-flex>
        <v-sheet height="500">
          <v-calendar
            v-model="today"
            :start="start"
            :end="end"
            color="primary"
            ref="calendar"
          >
            <template v-slot:day="{ date }">
              <template v-for="event in eventsMap[date]">
                <v-menu :key="event.title" v-model="event.open">
                  <template v-slot:activator="{ on }">
                    <div
                      v-if="!event.time"
                      v-ripple
                      class="my-event"
                      v-on="on"
                      v-html="event.title"
                    ></div>
                  </template>
                  <v-card color="grey lighten-4" min-width="350px">
                    <v-toolbar color="primary" dark>
                      <v-toolbar-title v-html="event.title"></v-toolbar-title>
                      <v-spacer></v-spacer>
                    </v-toolbar>
                    <v-card-title primary-title>
                      <span v-html="event.details"></span>
                    </v-card-title>
                    <v-card-actions>
                      <v-btn color="secondary"> Cancel </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-menu>
              </template>
            </template>
          </v-calendar>
        </v-sheet>
        <v-btn @click.prevent="nextMonth">
          Next
          <v-icon right dark> keyboard_arrow_right </v-icon>
        </v-btn>
      </v-flex>
    </v-form>
  </v-container>
</template>
<script>
export default {
  props: {
    showMenu: Boolean,
  },
  data: () => ({
    today: new Date().toISOString().substr(0, 10),
    events: [],
    start: new Date().toISOString().substr(0, 10),
    end: new Date().toISOString().substr(0, 10),
  }),
  created() {
    console.log(this.$store.state.user.userInfo);
    this.$store
      .dispatch(
        "schedule/profileSchedule",
        this.$store.state.user.userInfo.profile.id
      )
      .then((res) => {
        console.log(res.data);

        res.data.forEach((element) => {
          const a = {
            title: element.title,
            details: "Vs " + element.team[1].nameTeam,
            date: element.timeStart,
            open: false,
          };
          this.events.push(a);
        });
      });
  },
  computed: {
    eventsMap() {
      const map = {};
      this.events.forEach((e) => (map[e.date] = map[e.date] || []).push(e));
      return map;
    },
  },
  methods: {
    open(event) {
      alert(event.title);
    },
    nextMonth() {
      this.$refs.calendar.next();
      console.log();
    },
  },
  watch: {
    showMenu() {
      this.today = new Date().toISOString().substr(0, 10);
      (this.start = new Date().toISOString().substr(0, 10)),
        (this.end = new Date().toISOString().substr(0, 10));
    },
  },
};
</script>
<style  scoped>
.my-event {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  border-radius: 2px;
  background-color: #1867c0;
  color: #ffffff;
  border: 1px solid #1867c0;
  width: 100%;
  font-size: 12px;
  padding: 3px;
  cursor: pointer;
  margin-bottom: 1px;
}
</style>