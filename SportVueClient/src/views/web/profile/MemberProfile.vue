<template>
  <div style="background: whitesmoke" >
  <v-container>
    <v-form ref="form">
      <v-flex>
        <v-row>
          <v-col cols="6">
            <v-img
              lazy-src="https://picsum.photos/id/11/10/6"
              max-height="100"
              max-width="100"
              :src="info.profile.avatar"
            ></v-img>
          </v-col>
          <v-col cols="12" sm="6" md="6">
            <h3>{{ info.profile.name }}</h3>
            <p>{{ info.profile.address }}</p>
          </v-col>
          <v-col cols="12" sm="6" md="6">
            <v-icon>mdi-email</v-icon>{{ info.profile.email }}
          </v-col>
          <v-col cols="6">
            <v-icon>mdi-phone</v-icon>{{ info.profile.phone }}
          </v-col>
          <v-col cols="6">
            <v-icon>mdi-account</v-icon>{{ info.username }}
          </v-col>
          <v-col cols="6">
            <v-icon>mdi-account-group</v-icon>{{ this.team.nameTeam }}
                      </v-col>
              <v-col cols="6">
            <v-icon>mdi-tournament</v-icon>{{ this.team.tourName }}
          </v-col>
        </v-row>
        <h3>Schedule</h3>
        <h5>{{ this.start }}</h5>
        <v-sheet height="500">
          <v-calendar v-model="today" color="primary" ref="calendar">
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
        <v-btn @click.prevent="prewMonth">
          <v-icon right dark> keyboard_arrow_left </v-icon>
          Prew
        </v-btn>
        <v-btn @click.prevent="nextMonth">
          Next
          <v-icon right dark> keyboard_arrow_right </v-icon>
        </v-btn>
      </v-flex>
    </v-form>
  </v-container>
  </div>
</template>
<script>
const date = new Date().toISOString().substr(0, 10);

export default {
  props: {
    showMenu: Boolean,
  },
  data: () => ({
    today: new Date().toISOString().substr(0, 10),
    events: [],
    start: "",
    info: "",
    team: "",
  }),
  created() {
    this.info = this.$store.state.user.userInfo;
    console.log(this.info);
    this.start = date.slice(0, 7);
    this.$store
      .dispatch(
        "schedule/profileSchedule",
        this.$store.state.user.userInfo.profile.id
      )
      .then((res) => {
        console.log(res.data);
        if(res.data!=''){
        this.$store
          .dispatch("team/getTeam" , res.data[0].team[0].idTeam)
          .then((res) => {
            this.team = res.data;
          });
        }
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
      if (Number(this.start.substring(5, 7)) == 12) {
        this.start = (Number(this.start.substring(0, 4)) + 1)
          .toString()
          .concat("-01");
      } else {
        this.start = this.start
          .substring(0, 5)
          .concat(Number(this.start.substring(5, 7)) + 1);
      }
    },
    prewMonth() {
      this.$refs.calendar.prev();
      if (Number(this.start.substring(5, 7)) == 1) {
        this.start = (Number(this.start.substring(0, 4)) - 1)
          .toString()
          .concat("-12");
      } else {
        this.start = this.start
          .substring(0, 5)
          .concat(Number(this.start.substring(5, 7)) - 1);
      }
    },
  },
  watch: {
    showMenu() {
      this.start = date.slice(0, 7);
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